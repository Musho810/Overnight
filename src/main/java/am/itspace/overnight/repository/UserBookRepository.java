package am.itspace.overnight.repository;

import am.itspace.overnight.entity.UserBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;


public interface UserBookRepository extends JpaRepository<UserBook, Integer> {


    @Query(value = "from UserBook ub where ub.startDate BETWEEN :to AND :from OR ub.endDate BETWEEN :to AND :from")
    Page<UserBook> findByStartDateOrEndDateBetween(Pageable pageable, @Param("to") Date to, @Param("from") Date from);

    Page<UserBook> findByProductNameContaining(Pageable pageable, String keyword);

}

