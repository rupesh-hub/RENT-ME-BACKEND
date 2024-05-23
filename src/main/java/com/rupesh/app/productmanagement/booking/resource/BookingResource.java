package com.rupesh.app.productmanagement.booking.resource;

import com.rupesh.app.productmanagement.booking.model.BookingRequest;
import com.rupesh.app.productmanagement.booking.model.BookingResponse;
import com.rupesh.app.productmanagement.booking.service.IBookingService;
import com.rupesh.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
@Tag(name = "product_booking")
public class BookingResource {

    private final IBookingService bookingService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> createBooking(
            @RequestBody BookingRequest bookingRequest,
            Principal principal
    ) {
        bookingService.create(bookingRequest, principal);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/by.id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<BookingResponse>> getById(
            @PathVariable Long id,
            Principal principal
    ) {
        return ResponseEntity.ok(bookingService.findById(id, principal));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<BookingResponse>>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Principal principal
    ) {
        return ResponseEntity.ok(bookingService.findAll(page, size, principal));
    }

}
