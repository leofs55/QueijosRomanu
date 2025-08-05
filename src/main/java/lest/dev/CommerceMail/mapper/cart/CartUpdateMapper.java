package lest.dev.CommerceMail.mapper.cart;

import lest.dev.CommerceMail.dto.request.cart.CartUpdateRequest;
import lest.dev.CommerceMail.dto.response.cart.CartUpdateResponse;
import lest.dev.CommerceMail.dto.response.product.ProductResponse;
import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.entity.Product;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.mapper.product.ProductMapper;
import lest.dev.CommerceMail.mapper.user.UserMapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CartUpdateMapper {

    public static Cart map(CartUpdateRequest cartUpdateRequest) {

        List<Product> productList = cartUpdateRequest.products().stream()
                .map(cartCreateProductRequest -> Product.builder()
                        .id(cartCreateProductRequest.id())
                        .quantity(cartCreateProductRequest.quantity())
                        .build())
                .collect(Collectors.toList());

        return Cart.builder()
                .user(
                        User.builder()
                                .id(cartUpdateRequest.userId())
                                .build()
                )
                .products(productList)
                .build();
    }

    public static CartUpdateResponse map(Cart cart) {

        List<ProductResponse> list = cart.getProducts().stream()
                .map(ProductMapper::map)
                .toList();

        return CartUpdateResponse.builder()
                .productResponses(list)
                .totalPrice(cart.getTotalPrice())
                .user(UserMapper.map(cart.getUser()))
                .status(cart.getStatus())
                .build();
    }
}
