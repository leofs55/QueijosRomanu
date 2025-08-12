package lest.dev.CommerceMail.controller;

import jakarta.validation.Valid;
import lest.dev.CommerceMail.dto.request.cart.CartCreateRequest;
import lest.dev.CommerceMail.dto.request.cart.CartUpdateRequest;
import lest.dev.CommerceMail.dto.response.cart.CartCreateResponse;
import lest.dev.CommerceMail.dto.response.cart.CartResponse;
import lest.dev.CommerceMail.dto.response.cart.CartUpdateResponse;
import lest.dev.CommerceMail.dto.response.user.JWTUser;
import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.mapper.cart.CartCreateMapper;
import lest.dev.CommerceMail.mapper.cart.CartMapper;
import lest.dev.CommerceMail.mapper.cart.CartUpdateMapper;
import lest.dev.CommerceMail.service.CartService;
import lest.dev.CommerceMail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<CartCreateResponse> createCartEndPoint(@Valid @RequestBody CartCreateRequest cartCreateRequest,
                                                                 @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validateUserAccess(cartCreateRequest.userId(), jwtUser);

        CartCreateResponse response = CartCreateMapper.map(
                cartService.createCart(
                        CartCreateMapper.map(cartCreateRequest)
                )
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cart-open/{userId}")
    public ResponseEntity<CartResponse> findOpenCartByUserEndPoint(@PathVariable Long userId,
                                                                        @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validateUserAccess(userId, jwtUser);

        Cart cart = cartService.findOpenCart(userId);

        if (cart != null) {
            CartResponse cartResponse = CartMapper.map(
                    cart
            );
            return ResponseEntity.ok(cartResponse);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<CartResponse>> findAllCartByUserEndPoint(@PathVariable Long userId,
                                                                        @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validateUserAccess(userId, jwtUser);

        List<CartResponse> cartList = cartService.findAllByUserId(userId).stream()
                .map(CartMapper::map)
                .toList();
        return ResponseEntity.ok(cartList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartUpdateResponse> updateCartEndPoint(@PathVariable String id,
                                                                 @Valid @RequestBody CartUpdateRequest cartUpdateRequest,
                                                                 @AuthenticationPrincipal JWTUser jwtUser) {
        userService.validateUserAccess(cartUpdateRequest.userId(), jwtUser);

        Cart cart = CartUpdateMapper.map(cartUpdateRequest);
        CartUpdateResponse response = CartUpdateMapper.map(
                cartService.updateCart(id, cart)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> findCartByIdEndPoint(@PathVariable String id,
                                                             @AuthenticationPrincipal JWTUser jwtUser) {

        Cart cart = cartService.findCart(id);

        userService.validateUserAccess(cart.getUser().getId(), jwtUser);

        CartResponse response = CartMapper.map(cart);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartResponse>> findAllCartsEndPoint(@AuthenticationPrincipal JWTUser jwtUser) {

        userService.validateUserAccess(jwtUser.id(), jwtUser);

        userService.validatePermissionUser(jwtUser);

        List<CartResponse> cartList = cartService.findAllCarts().stream()
                .map(CartMapper::map)
                .toList();
        return ResponseEntity.ok(cartList);
    }

    @GetMapping("/sold/{id}")
    public ResponseEntity<CartResponse> soldCartEndPoint(@PathVariable String id,
                                                         @AuthenticationPrincipal JWTUser jwtUser) {

        userService.validatePermissionUser(jwtUser);

        CartResponse cartResponse = CartMapper.map(cartService.soldCart(id));

        return ResponseEntity.ok(cartResponse);
    }
    
}
