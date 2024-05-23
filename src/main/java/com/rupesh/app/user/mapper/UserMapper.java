package com.rupesh.app.user.mapper;

import com.rupesh.app.user.entity.User;
import com.rupesh.app.user.model.UserResponse;

public final class UserMapper {

    private UserMapper() {}

    public static UserResponse toResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setAccountLocked(user.isAccountLocked());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEnabled(user.isEnabled());
        userResponse.setCreatedDate(user.getCreatedDate());
        return userResponse;
    }
}
