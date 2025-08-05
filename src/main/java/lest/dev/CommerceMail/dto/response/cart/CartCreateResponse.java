package lest.dev.CommerceMail.dto.response.cart;

import lest.dev.CommerceMail.dto.response.product.ProductResponse;
import lest.dev.CommerceMail.dto.response.user.UserResponse;
import lest.dev.CommerceMail.enums.Status;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CartCreateResponse(List<ProductResponse> productResponses,
                                 BigDecimal totalPrice,
                                 UserResponse user,
                                 Status status) {
}
