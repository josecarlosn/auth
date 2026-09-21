package br.com.josecarlosn.auth.repository;

import br.com.josecarlosn.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
