package lest.dev.CommerceMail.service;

import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.exception.product.*;
import lest.dev.CommerceMail.mapper.product.ProductMapper;
import lest.dev.CommerceMail.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lest.dev.CommerceMail.exception.category.CategoryNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    public Product createProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product object cannot be null.");
        }
        try {
            product.setCategory(categoryService.findById(product.getCategory().getId()));
            return repository.save(product);
        } catch (Exception e) {
            throw new ProductCreationException("Failed to create product. Reason: " + e.getMessage(), e);
        }
    }

    public Product findProduct(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID cannot be null.");
        }
        return repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
    }

    public List<Product> findAllProduct() {
        List<Product> productList = repository.findAll();
        return productList;
    }

    public Product updateProduct(Long id, Product product) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null for update.");
        }
        if (product == null) {
            throw new IllegalArgumentException("Product object cannot be null for update.");
        }

        Product productInDb = findProduct(id);

        product.setId(productInDb.getId());

        product.setCategory(categoryService.findById(product.getCategory().getId()));
        try {
            return repository.save(product);
        } catch (Exception e) {
            throw new ProductUpdateException("Failed to update product with ID: " + id + ". Reason: " + e.getMessage(), e);
        }
    }

    public List<Product> reduceProductsInDb(List<Map<String, Product>> mapList) {
        try {

            List<Product> newProductsForDb = new ArrayList<>();
            List<Product> productsForCart = new ArrayList<>();

            for (Map<String, Product> map : mapList){
                Product product = map.get("product");
                Product productInDb = map.get("productInDb");

                if (productInDb.getQuantity() < product.getQuantity()) {
                    throw new ProductsQuatityExceededException("Quantity of product"+ productInDb.getName() + " is exceeded!");
                }
                productInDb.setQuantity(Integer.valueOf(
                        productInDb.getQuantity()- product.getQuantity()
                ));
                product.setCategory(productInDb.getCategory());
                productsForCart.add(ProductMapper.map(productInDb, product.getQuantity()));
                newProductsForDb.add(productInDb);
            }
            repository.saveAll(newProductsForDb);

            return productsForCart;

        } catch (ProductsQuatityExceededException e) {
            throw new ProductsQuatityExceededException(e.getLocalizedMessage(), e);
        }
    }

    public List<Map<String, Product>> findProductInDb(List<Product> listProducts) {
        if (listProducts == null) {
            return new ArrayList<>();
        }

        List<Map<String, Product>> productsFinds = new ArrayList<>();

        for (Product product : listProducts) {
            try {

                Product productInDb = findProduct(product.getId());
                productsFinds.add(Map.of(
                        "product", product,
                        "productInDb", productInDb)
                );

            } catch (ProductNotFoundException e) {
                throw new ProductNotFoundException("Product with ID " + product.getId() + " not found while processing list. Skipping.", e);
            } catch (Exception e) {
                throw new RuntimeException("An unexpected error occurred for product ID " + product.getId() + ": " + e.getMessage(), e);
            }
        }
        return productsFinds;
    }

    public void deleteProduct(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null for deletion.");
        }

        try {
            if (findProduct(id) != null) {
                repository.deleteById(id);
            }
        } catch (Exception e) {
            throw new ProductDeletionException("Failed to delete product with ID: " + id + ". Reason: " + e.getMessage(), e);
        }
    }

    public List<Product> findProductsByCategory(Long categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null.");
        }
        try {
            if(categoryService.findById(categoryId) == null) {
                throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
            }
            return repository.findProductsByCategoryId(categoryId);
        } catch (Exception e) {
            throw new ProductNotFoundException("Products not found with Category ID: " + categoryId);
        }
    }

}
