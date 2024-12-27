package ua.nure.kryvko.roman.Atark.user;

public record User(
        Integer id,
        String login,
        String email,
        String password,
        UserRole role
) {
}
