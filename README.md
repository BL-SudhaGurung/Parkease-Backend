# ParkEase - Smart Parking Management Platform

## Overview

ParkEase is a Smart Parking Management Platform built using Java Spring Boot Microservices.

The system allows drivers to search, reserve, and pay for parking spaces while enabling parking lot managers to manage parking facilities. Administrators monitor the complete platform through an analytics dashboard.

---

## Features

### Driver

- Register/Login
- OAuth2 Login
- Search Nearby Parking Lots
- Reserve Parking Spot
- Register Vehicles
- Online Payment
- Booking History
- Notifications

### Lot Manager

- Register Parking Lot
- Manage Parking Spots
- View Bookings
- Revenue Dashboard
- Occupancy Dashboard

### Admin

- Manage Users
- Approve Parking Lots
- Platform Analytics
- Revenue Reports
- Broadcast Notifications

---

## Architecture

The application follows a Microservices Architecture.

Services include:

- Auth Service
- Parking Lot Service
- Parking Spot Service
- Booking Service
- Vehicle Service
- Payment Service
- Notification Service
- Analytics Service

---

## Tech Stack

Backend
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

Database
- PostgreSQL

Authentication
- JWT
- OAuth2

Documentation
- Swagger

Build Tool
- Maven

Containerization
- Docker

Version Control
- Git

---


## Project Structure

```
parkease

├── api-gateway
├── service-registry
├── config-server
├── auth-service
├── parkinglot-service
├── parkingspot-service
├── booking-service
├── vehicle-service
├── payment-service
├── notification-service
├── analytics-service
└── frontend
```

---

## Roles

### Driver

- Register
- Login
- Book Parking
- Make Payment
- View Booking History

### Lot Manager

- Create Parking Lot
- Manage Parking Spots
- View Revenue
- View Occupancy

### Admin

- Approve Parking Lots
- Manage Users
- Platform Reports

---

## Booking Flow

1. Driver searches parking lot.
2. Selects available parking spot.
3. Creates booking.
4. Spot status changes to Reserved.
5. Driver checks in.
6. Spot status changes to Occupied.
7. Driver checks out.
8. Fare is calculated.
9. Payment is processed.
10. Receipt is generated.

---

## Security

- JWT Authentication
- OAuth2 Login (Google/GitHub)
- Role-Based Authorization
- Password Encryption

---

## APIs

Each microservice exposes REST APIs documented using Swagger.

Example:

```
/auth/**
/parking-lots/**
/spots/**
/bookings/**
/vehicles/**
/payments/**
/notifications/**
/analytics/**
```

---

## Future Enhancements

- QR Code Based Check-In
- Dynamic Pricing
- AI Parking Prediction
- Mobile Application
- EV Charging Reservation
- Google Maps Integration

---

## Author

Developed as a Microservices-based Smart Parking Management System using Java Spring Boot.
