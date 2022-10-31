package am.itspace.overnight.repository;

import am.itspace.overnight.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttributeRepository extends JpaRepository<Attribute, Integer> {


    Optional<Attribute> findByNameStartsWith(String text);

    Optional<Attribute> findByNameContaining(String text);


}
