package am.itspace.overnight.repository;

import am.itspace.overnight.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute,Integer> {
}
