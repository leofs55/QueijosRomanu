package lest.dev.CommerceMail.repository;

import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

    List<Cart> findAllByUserId(Long userId);

    Optional<Cart> findCartByUserIdAndStatus(Long userId, Status status);
}
