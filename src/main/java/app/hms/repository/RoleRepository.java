package app.hms.repository;

import app.hms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Optional<Role> findByName(String role);
}
