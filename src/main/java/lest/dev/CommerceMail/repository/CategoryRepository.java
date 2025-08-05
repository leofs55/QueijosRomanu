package lest.dev.CommerceMail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lest.dev.CommerceMail.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
