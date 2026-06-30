package com.parkease.auth.repository;

import com.parkease.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    User findByUserId(Integer userId);

    boolean existsByEmail(String email);

    List<User> findAllByRole(String role);

    Optional<User> findByVehiclePlate(String vehiclePlate);

    Optional<User> findByPhone(String phone);

    Void deleteByUserId(Integer userId);

    User findByUsername(String username);

    User getByUsername(String username);


    boolean existsByUsername(String username);
}
