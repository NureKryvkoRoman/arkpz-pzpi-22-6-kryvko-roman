package ua.nure.kryvko.roman.Atark.userinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.kryvko.roman.Atark.user.User;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByUser(User user);
    boolean existsByUser(User user);
}
