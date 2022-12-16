package am.itspace.overnight.service;

import am.itspace.overnight.entity.Product;
import am.itspace.overnight.exception.ProductNotFoundException;
import am.itspace.overnight.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    @Value("${overnight.images.folder}")
    private String folderPath;

    private final ProductRepository productRepository;

    public Product addProduct(Product product, MultipartFile file) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            product.setPicUrl(fileName);
        }
        return productRepository.save(product);
    }

    public Page<Product> getAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    public Optional<Product> getById(int productId) {
        return productRepository.findById(productId);
    }

    public void update(Product product) {
        Optional<Product> byId = productRepository.findById(product.getId());
        if (byId.isPresent()) {
            byId.get().setName(product.getName());
            byId.get().setAddress(product.getAddress());
            byId.get().setDescription(product.getDescription());
            byId.get().setType(product.getType());
            byId.get().setPicUrl(product.getPicUrl());
            byId.get().setRating(product.getRating());
            byId.get().setGoogleAddress(product.getGoogleAddress());
            byId.get().setStatus(product.getStatus());
            byId.get().setRatingUserCount(product.getRatingUserCount());
            byId.get().setUser(product.getUser());
            byId.get().setCityVillage(product.getCityVillage());
            productRepository.save(byId.get());
        } else {
            throw new ProductNotFoundException(product.getId());
        }
    }

    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }
}
