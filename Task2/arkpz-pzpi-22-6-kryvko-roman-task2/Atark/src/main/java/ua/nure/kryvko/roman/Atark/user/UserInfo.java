package ua.nure.kryvko.roman.Atark.user;

import java.time.LocalDateTime;

public record UserInfo(
        Integer id,
        Integer userId,
        LocalDateTime createdAt,
        LocalDateTime lastLogin,
        String firstName,
        String lastName,
        String phoneNumber
) {
}
