package com.rupesh.app.productmanagement.booking.repository;

import com.rupesh.app.productmanagement.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
