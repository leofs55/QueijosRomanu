package lest.dev.CommerceMail.dto.response.product;

import lest.dev.CommerceMail.dto.response.category.CategoryResponse;
import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record ProductUpdateResponse(String name,
                                    String description,
                                    BigDecimal price,
                                    BigDecimal grams,
                                    Integer quantity,
                                    String imgUrl,
                                    CategoryResponse categoryResponse) {
}
