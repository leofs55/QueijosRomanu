package lest.dev.CommerceMail.mapper.cart;

import lest.dev.CommerceMail.dto.request.cart.CartCreateRequest;
import lest.dev.CommerceMail.dto.response.cart.CartCreateResponse;
import lest.dev.CommerceMail.dto.response.product.ProductResponse;
import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.enums.Status;
import lest.dev.CommerceMail.mapper.product.ProductMapper;
import lest.dev.CommerceMail.mapper.user.UserMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CartCreateMapper {

    public static Cart map(CartCreateRequest cartCreateRequest) {

        List<Product> productList = cartCreateRequest.products().stream()
                .map(cartCreateProductRequest -> Product.builder()
                        .id(cartCreateProductRequest.id())
                        .quantity(cartCreateProductRequest.quantity())
                        .build())
                .collect(Collectors.toList());

        return Cart.builder()
                .products(productList)
                .user(User.builder()
                        .id(cartCreateRequest.userId())
                        .build())
                .status(Status.valueOf(cartCreateRequest.status()))
                .build();
    }

    public static CartCreateResponse map(Cart cart) {

        List<ProductResponse> list = cart.getProducts().stream()
                .map(ProductMapper::map)
                .toList();

        return CartCreateResponse.builder()
                .productResponses(list)
                .totalPrice(cart.getTotalPrice())
                .user(UserMapper.map(cart.getUser()))
                .status(cart.getStatus())
                .build();
    }
}
