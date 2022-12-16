package am.itspace.overnight.service;

import am.itspace.overnight.entity.Product;
import am.itspace.overnight.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


}
