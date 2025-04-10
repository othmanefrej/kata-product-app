package ma.kata.product_app.mapper;

import ma.kata.product_app.dto.user.UserResponseDto;
import ma.kata.product_app.model.User;

public class UserMapper {

    public static UserResponseDto toResponse(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
