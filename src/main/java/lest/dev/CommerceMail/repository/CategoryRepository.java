package lest.dev.CommerceMail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lest.dev.CommerceMail.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
           "FROM Product p WHERE p.category.id = :categoryId")
    boolean existsProductInCategory(@Param("categoryId") Long categoryId);
}
