package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Product;
import am.itspace.overnight.security.CurrentUser;
import am.itspace.overnight.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    @GetMapping("/products")
    public String adminPage(@PageableDefault(size = 5) Pageable pageable,
                            ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {

        Page<Product> productList = productService.getAll(pageable);
        int totalPages = productList.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("products", productList);
        log.info("Controller/adminPage called by {}", currentUser.getUsername());
        return "seller/products";
    }
    @PostMapping("/products/add")
    public String addProducts(
            @ModelAttribute Product product,
            @RequestParam(value = "userImage", required = false) MultipartFile file
    ) throws IOException {
        Product savedProduct = productService.addProduct(product, file);
        return "redirect:/single-products?id=" + savedProduct.getId();
    }
    @GetMapping("/single-products")
    public String adminPage(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<Product> byId = productService.getById(id);
        modelMap.addAttribute("product", byId.get());
        return "single-products";
    }
    @GetMapping ("/products/remove")
    public String removeProduct(@RequestParam("id") int id){
        productService.deleteById(id);
        return "/products";
    }
}
