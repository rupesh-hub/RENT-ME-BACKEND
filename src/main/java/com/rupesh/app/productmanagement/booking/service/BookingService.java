package com.rupesh.app.productmanagement.booking.service;

import com.rupesh.app.exception.RentMeException;
import com.rupesh.app.productmanagement.booking.entity.Booking;
import com.rupesh.app.productmanagement.booking.mapper.BookingMapper;
import com.rupesh.app.productmanagement.booking.model.BookingRequest;
import com.rupesh.app.productmanagement.booking.model.BookingResponse;
import com.rupesh.app.productmanagement.booking.repository.BookingRepository;
import com.rupesh.app.productmanagement.product.model.Paging;
import com.rupesh.app.productmanagement.product.repository.ProductRepository;
import com.rupesh.app.user.repository.UserRepository;
import com.rupesh.app.util.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public GlobalResponse<Void> create(BookingRequest request, Principal principal) {

        productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RentMeException("Product not found by product id " + request.getProductId()));

        var user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RentMeException("User not found by email " + principal.getName()));

        var booking = BookingMapper.toEntity(request);
        booking.setCreatedDate(LocalDateTime.now());
        booking.setUserId(user.getId());

        bookingRepository.save(booking);

        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<BookingResponse> findById(Long id, Principal principal) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RentMeException("Booking not found by id " + id));

        var product = productRepository.findById(booking.getProductId())
                .orElseThrow(() -> new RentMeException("Product not found by id " + booking.getProductId()));

        return GlobalResponse.success(
                BookingResponse
                        .builder()
                        .id(booking.getId())
                        .requestedDate(booking.getCreatedDate())
                        .bookingDate(booking.getBookingDate())
                        .returnDate(booking.getReturnDate())
                        .modifiedDate(booking.getLastModifiedDate())
                        .username(principal.getName())
                        .productName(product.getName())
                        .productPrice(product.getPrice())
                        .build()
        );
    }

    @Override
    public GlobalResponse<List<BookingResponse>> findAll(int page, int size, Principal principal) {

        Page<Booking> bookingPage = bookingRepository.findAll(PageRequest.of(page, size));

        List<BookingResponse> productResponses = new ArrayList<>();

        for (Booking booking : bookingPage.getContent()) {
            var product = productRepository.findById(booking.getProductId())
                    .orElseThrow(() -> new RentMeException("Product not found by id " + booking.getProductId()));

            productResponses.add(
                    BookingResponse
                            .builder()
                            .id(booking.getId())
                            .username(principal.getName())
                            .productName(product.getName())
                            .productPrice(product.getPrice())
                            .bookingDate(booking.getBookingDate())
                            .modifiedDate(booking.getLastModifiedDate())
                            .returnDate(booking.getReturnDate())
                            .requestedDate(booking.getCreatedDate())
                            .build()
            );

        }

        return GlobalResponse.success(
                productResponses,
                Paging.builder()
                        .page(page)
                        .size(size)
                        .totalElement(bookingPage.getTotalElements())
                        .totalPage(bookingPage.getTotalPages())
                        .first(bookingPage.isFirst())
                        .last(bookingPage.isLast())
                        .build()
        );

    }

    @Override
    public GlobalResponse<Void> update(BookingRequest bookingRequest, Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RentMeException("Booking not found by id " + id));

        booking.setProductId(bookingRequest.getProductId());
        booking.setBookingDate(bookingRequest.getBookingDate());
        booking.setReturnDate(bookingRequest.getReturnDate());
        booking.setLastModifiedDate(LocalDateTime.now());
        bookingRepository.save(booking);
        return GlobalResponse.success();
    }

    @Override
    public GlobalResponse<Void> delete(Long id) {
        var booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RentMeException("Booking not found by id " + id));
        bookingRepository.delete(booking);
        return GlobalResponse.success();
    }
}
