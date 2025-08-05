package lest.dev.CommerceMail.dto.response.product;

import lest.dev.CommerceMail.dto.response.category.CategoryResponse;
import lombok.Builder;  

import java.math.BigDecimal;

@Builder
public record ProductResponse(Long id,
                              String name,
                              String description,
                              BigDecimal price,
                              Integer quantity,
                              BigDecimal grams,
                              String imgUrl,
                              CategoryResponse categoryResponse) {
}
