package lest.dev.CommerceMail.dto.request.cart;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CartCreateRequest(@NotEmpty(message = "A lista de produtos é obrigatória")
                                List<CartCreateProductRequest> products,
                                @NotNull(message = "O id do usuário é obrigatório")
                                Long userId,
                                @Pattern(regexp = "OPEN|SOLD", message = "O status é obrigatorio.")
                                String status) {
}
