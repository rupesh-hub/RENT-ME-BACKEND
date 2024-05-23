package com.rupesh.app.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private String firstName;
    private String lastName;
    private String email;
    private boolean accountLocked;
    private boolean enabled;
    private LocalDateTime createdDate;

}
