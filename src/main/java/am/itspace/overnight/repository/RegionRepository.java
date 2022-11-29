package am.itspace.overnight.repository;

import am.itspace.overnight.entity.Attribute;
import am.itspace.overnight.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region,Integer> {

    List<Region> findByNameContaining(String keyword);


}
