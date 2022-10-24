package am.itspace.overnight.repository;

import am.itspace.overnight.entity.ProductGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductGalleryRepository extends JpaRepository<ProductGallery,Integer> {
}
