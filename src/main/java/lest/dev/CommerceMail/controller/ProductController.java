package lest.dev.CommerceMail.controller;

import jakarta.validation.Valid;
import lest.dev.CommerceMail.dto.request.product.ProductCreateRequest;
import lest.dev.CommerceMail.dto.request.product.ProductUpdateRequest;
import lest.dev.CommerceMail.dto.response.product.ProductCreateResponse;
import lest.dev.CommerceMail.dto.response.product.ProductResponse;
import lest.dev.CommerceMail.dto.response.product.ProductUpdateResponse;
import lest.dev.CommerceMail.dto.response.user.JWTUser;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.mapper.product.ProductCreateMapper;
import lest.dev.CommerceMail.mapper.product.ProductMapper;
import lest.dev.CommerceMail.mapper.product.ProductUpdateMapper;
import lest.dev.CommerceMail.service.ProductService;
import lest.dev.CommerceMail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ProductCreateResponse> createProductEndPoint(@Valid @RequestBody ProductCreateRequest productCreateRequest,
                                                                       @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        Product product = ProductCreateMapper.map(productCreateRequest);
        ProductCreateResponse response = ProductCreateMapper.map(
                productService.createProduct(product)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductEndPoint(@PathVariable Long id) {
        ProductResponse response = ProductMapper.map(
                productService.findProduct(id)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> findAllProductsEndPoint() {
        List<ProductResponse> productResponseList =  productService.findAllProduct().stream()
                .map(ProductMapper::map)
                .toList();
        return ResponseEntity.ok(productResponseList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductUpdateResponse> updateProductEndPoint(@PathVariable Long id,
                                                                       @Valid @RequestBody ProductUpdateRequest productUpdateRequest,
                                                                       @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        Product product = ProductUpdateMapper.map(productUpdateRequest);
        ProductUpdateResponse response = ProductUpdateMapper.map(
                productService.updateProduct(id, product)
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductEndPoint(@PathVariable Long id,
                                                      @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> findProductsByCategoryEndPoint(@PathVariable Long categoryId) {
        List<ProductResponse> productResponseList = productService.findProductsByCategory(categoryId).stream()
                .map(ProductMapper::map)
                .toList();
        return ResponseEntity.ok(productResponseList);
    }
}
