package lest.dev.CommerceMail.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        @NotBlank(message = "O nome do produto é obrigatório")
        String name,

        @NotBlank(message = "A descrição do produto é obrigatória")
        String description,

        @NotNull(message = "O preço do produto é obrigatório")
        BigDecimal price,

        @NotNull(message = "O peso do produto (em gramas) é obrigatório")
        BigDecimal grams,

        @NotNull(message = "A quantidade do produto é obrigatória")
        Integer quantity,
        
        @NotBlank(message = "A URL da imagem do produto é obrigatória")
        String imgUrl,

        @NotNull(message = "O id da categoria é obrigatório")
        Long categoryId
) {}