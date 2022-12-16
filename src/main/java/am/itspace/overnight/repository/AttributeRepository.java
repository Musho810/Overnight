package am.itspace.overnight.repository;

import am.itspace.overnight.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

    List<Attribute> findByNameContaining(String keyword);

}
