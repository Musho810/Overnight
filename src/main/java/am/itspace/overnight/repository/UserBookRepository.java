package am.itspace.overnight.repository;

import am.itspace.overnight.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook,Integer> {
}
