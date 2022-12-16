package am.itspace.overnight.repository;


import am.itspace.overnight.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttributeValueRepository extends JpaRepository<AttributeValue,Integer> {

//    @Query(value = "SELECT p FROM ")
//    List<AttributeValue> findAttributeSearchResult(@Param("attribute") String attribute);
}
