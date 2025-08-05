package lest.dev.CommerceMail.mapper.cart;

import lest.dev.CommerceMail.dto.response.cart.CartResponse;
import lest.dev.CommerceMail.dto.response.product.ProductResponse;
import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.mapper.product.ProductMapper;
import lest.dev.CommerceMail.mapper.user.UserMapper;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CartMapper {

    public static CartResponse map(Cart cart) {

        List<ProductResponse> responseList = cart.getProducts().stream()
                .map(ProductMapper::map)
                .toList();

        return CartResponse.builder()
                .id(cart.getId())
                .productResponses(responseList)
                .totalPrice(cart.getTotalPrice())
                .user(UserMapper.map(cart.getUser()))
                .status(cart.getStatus())
                .build();
    }
}
