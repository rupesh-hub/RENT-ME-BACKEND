package com.rupesh.app.productmanagement.booking.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {

    private Long id;
    private String username;
    private String productName;
    private double productPrice;
    private LocalDateTime bookingDate;
    private LocalDateTime returnDate;
    private LocalDateTime requestedDate;
    private LocalDateTime modifiedDate;

}
