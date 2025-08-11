package lest.dev.CommerceMail.service;

import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.enums.Status;
import lest.dev.CommerceMail.enums.UsersRoles;
import lest.dev.CommerceMail.exception.User.UserAccessDennied;
import lest.dev.CommerceMail.exception.User.UserDontHaveCartOpenException;
import lest.dev.CommerceMail.exception.User.UserNotFoundException;
import lest.dev.CommerceMail.exception.cart.CartCreationException;
import lest.dev.CommerceMail.exception.cart.CartNotFoundException;
import lest.dev.CommerceMail.exception.cart.CartUpdateException;
import lest.dev.CommerceMail.exception.product.ProductNotFoundException;
import lest.dev.CommerceMail.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final UserService userService;
    private final ProductService productService;

    public Cart createCart(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart object cannot be null.");
        }
        if (cart.getUser() == null || cart.getUser().getId() == null) {
            throw new IllegalArgumentException("Cart must be associated with a user and user ID cannot be null.");
        }

        if (repository.findCartByUserIdAndStatus(cart.getUser().getId(), Status.OPEN).isPresent()) {
            throw new CartCreationException("Existing open cart!");
        }

        try {
            User userInDb = userService.findUserById(cart.getUser().getId());
            // List<Products> listProductsWithIdAndQuantity -> List<Products> listProductsForClient
            List<Map<String, Product>> productsInDb = productService.findProductInDb(cart.getProducts());
        
            log.info(productsInDb.toString());
            cart.setProducts(productService.reduceProductsInDb(productsInDb));
            cart.setUser(userInDb);
            cart.calculateTotalPrice();
            log.info(cart.toString());
            return repository.save(cart);
        } catch (UserNotFoundException | ProductNotFoundException e) {
            throw new CartCreationException("Failed to create cart due to missing user or product data: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new CartCreationException("Failed to create cart: " + e.getLocalizedMessage(), e);
        }
    }

    public Cart findCart(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be null or empty.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + id));
    }

    public List<Cart> findAllByUserId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null to find carts.");
        }

        List<Cart> cartList = repository.findAllByUserId(id);

        return cartList;
    }

    public Cart updateCart(String id, Cart cart) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Cart ID cannot be null or empty for update.");
        }
        if (cart == null) {
            throw new IllegalArgumentException("Cart object cannot be null for update.");
        }
        if (cart.getUser() == null || cart.getUser().getId() == null) {
            throw new IllegalArgumentException("Cart must be associated with a user and user ID cannot be null for update.");
        }

        try {
            Cart cartInDb = findCart(id);
            User userInDb = userService.findUserById(cart.getUser().getId());
            List<Map<String, Product>> productsInDb = productService.findProductInDb(cart.getProducts());

            cart.setId(cartInDb.getId());
            cart.setProducts(productService.reduceProductsInDb(productsInDb));
            cart.setUser(userInDb);
            cart.setStatus(cartInDb.getStatus());
            cart.calculateTotalPrice();

            return repository.save(cart);
        } catch (CartNotFoundException e) {
            throw e;
        } catch (UserNotFoundException | ProductNotFoundException e) {
            throw new CartUpdateException("Failed to update cart due to missing user or product data: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new CartUpdateException("Failed to update cart with ID " + id + ": " + e.getMessage(), e);
        }
    }

    public Cart findOpenCart(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null to find carts.");
        }
        try {
            Optional<Cart> cartOpen = repository.findCartByUserIdAndStatus(userId, Status.OPEN);
            return cartOpen.orElseThrow(() -> new UserDontHaveCartOpenException("User dont have cart open!"));

        } catch (UserDontHaveCartOpenException e) {
            throw new UserDontHaveCartOpenException(e.getLocalizedMessage(), e);
        }
    }

    public void validateUserOwnerCart(String cartId, Long id){
        try {

            User user = userService.findUserById(id);

            Cart cart = findCart(cartId);

            if (!user.getId().equals(cart.getUser().getId()) && user.getUserRole() == UsersRoles.USER) {
                throw new UserAccessDennied("This user don't have permission for access ");
            }

        } catch (Exception e) {
            throw new UserAccessDennied(e.getLocalizedMessage(), e);
        }
    }

    public List<Cart> findAllCarts() {
        return repository.findAll();
    }
}
