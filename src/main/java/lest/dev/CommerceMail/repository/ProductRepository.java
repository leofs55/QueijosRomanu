package lest.dev.CommerceMail.repository;

import lest.dev.CommerceMail.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
