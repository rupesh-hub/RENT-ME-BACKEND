package com.rupesh.app.productmanagement.booking.service;

import com.rupesh.app.productmanagement.booking.model.BookingRequest;
import com.rupesh.app.productmanagement.booking.model.BookingResponse;
import com.rupesh.app.util.GlobalResponse;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.List;

public interface IBookingService {

    GlobalResponse<Void> create(BookingRequest bookingRequest, Principal principal);

    GlobalResponse<BookingResponse> findById(Long id, Principal principal);

    GlobalResponse<List<BookingResponse>> findAll(int page, int size, Principal principal);

    GlobalResponse<Void> update(BookingRequest bookingRequest, Long id);

    GlobalResponse<Void> delete(Long id);

}
