package lest.dev.CommerceMail.repository;

import lest.dev.CommerceMail.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategoryId(Long categoryId);
}
