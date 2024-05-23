package com.rupesh.app.productmanagement.booking.resource;

import com.rupesh.app.productmanagement.booking.service.IBookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
@Tag(name="product_booking")
public class BookingResource {

    private final IBookingService bookingService;

}
