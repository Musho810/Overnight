package am.itspace.overnight.repository;

import am.itspace.overnight.entity.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface UserBookRepository extends JpaRepository<UserBook, Integer> {


    Page<UserBook> findByStartDateAfterAndEndDateBefore(Pageable pageable, Date startDate, Date endDate);

    Page<UserBook> findByProductNameContaining(Pageable pageable, String keyword);

}

