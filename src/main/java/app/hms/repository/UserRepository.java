package app.hms.repository;

import app.hms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
           Optional<User> findByUsernameOrEmail(String username,String email);
}
