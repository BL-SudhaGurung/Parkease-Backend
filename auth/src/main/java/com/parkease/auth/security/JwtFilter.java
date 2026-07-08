package com.parkease.auth.security;
import com.parkease.auth.service.CustomerUserDetails;
import com.parkease.auth.service.TokenBlacklistService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerUserDetails customerUserDetails;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/login/oauth2/") ||
               path.startsWith("/oauth2/") ||
               path.startsWith("/api/v1/auth/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        log.info("Processing JWT filter for request: {} with header: {}", request.getRequestURI(), authHeader);

        String token = null;
        String username = null;

        // extract token from header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            log.info("Extracted token from request: {}", request.getRequestURI());
            log.info("Extracted token: {}", token.substring(0,7));
            try {
                username = jwtUtil.extractUsername(token);
            } catch (ExpiredJwtException e) {
                log.error("Expired JWT token for request: {}", request.getRequestURI());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"TOKEN_EXPIRED\",\"message\":\"Access token expired, please refresh\"}");
                return;
            }
        }

        // reject blacklisted tokens
        if (token != null && tokenBlacklistService.isBlacklisted(token)) {
            log.warn("Blacklisted token used for request: {}", request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"TOKEN_BLACKLISTED\",\"message\":\"Token has been logged out\"}");
            return;
        }

        // set authentication in context if valid
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = customerUserDetails.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, username)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("Authenticated user: {} and role : {}", username,auth.getAuthorities());
            }
        }

        // always continue the filter chain
        filterChain.doFilter(request, response);
    }
}
