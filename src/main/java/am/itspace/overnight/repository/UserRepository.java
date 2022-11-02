package am.itspace.overnight.repository;

import am.itspace.overnight.entity.RoleUser;
import am.itspace.overnight.entity.StatusSeller;
import am.itspace.overnight.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String username);

    Page<User> findUserByRole(RoleUser roleUser, Pageable pageable);
}
