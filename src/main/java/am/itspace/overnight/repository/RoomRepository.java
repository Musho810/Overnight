package am.itspace.overnight.repository;


import am.itspace.overnight.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query(value = "SELECT p from Room p join p.product.cityVillage.region r left join" +
            " UserBook ub on p.product = ub.product where r.id = " +
            ":regionId and p.bedsCount = :bedsCount and p.bedsCountChildren=:bedsCountChildren " +
            "and :endDate  NOT BETWEEN ub.startDate and ub.endDate and :startDate  NOT BETWEEN ub.startDate and ub.endDate ")
    Page<Room> findByCriteria(@Param("regionId") int id,
                              @Param("endDate") Date endDate,
                              @Param("startDate") Date startDate,
                              @Param("bedsCount") int bedsCount,
                              @Param("bedsCountChildren") int bedsCountChildren,
                              Pageable pageable);



    List<Room> findTop6ByOrderByProduct_RatingDesc();

}


