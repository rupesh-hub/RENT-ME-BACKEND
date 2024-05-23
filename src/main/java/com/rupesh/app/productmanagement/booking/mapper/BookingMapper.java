package com.rupesh.app.productmanagement.booking.mapper;

import com.rupesh.app.productmanagement.booking.entity.Booking;
import com.rupesh.app.productmanagement.booking.model.BookingRequest;
import com.rupesh.app.productmanagement.booking.model.BookingResponse;

public final class BookingMapper {

    private BookingMapper() {}

    public static Booking toEntity(BookingRequest request) {
        return Booking
                .builder()
                .bookingDate(request.getBookingDate())
                .returnDate(request.getReturnDate())
                .productId(request.getProductId())
                .build();
    }

    public static BookingResponse toResponse(Booking booking) {
        return BookingResponse
                .builder()
                .id(booking.getId())
                .bookingDate(booking.getBookingDate())
                .returnDate(booking.getReturnDate())
                .modifiedDate(booking.getLastModifiedDate())
                .requestedDate(booking.getCreatedDate())
                .build();
    }
}
