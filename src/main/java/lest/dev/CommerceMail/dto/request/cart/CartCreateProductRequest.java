package lest.dev.CommerceMail.dto.request.cart;

import jakarta.validation.constraints.NotNull;

public record CartCreateProductRequest(@NotNull(message = "O id do produto é obrigatório.")
                                       Long id,
                                       @NotNull(message = "A quantidade do produto é obrigatório.")
                                       Integer quantity) {
}
