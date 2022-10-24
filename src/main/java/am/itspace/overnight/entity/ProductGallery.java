package am.itspace.overnight.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_galerry")
public class ProductGallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pictureUrl;
    @ManyToOne
    private Product product;

}
