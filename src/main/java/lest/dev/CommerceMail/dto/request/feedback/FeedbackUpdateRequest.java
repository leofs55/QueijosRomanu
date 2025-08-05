package lest.dev.CommerceMail.dto.request.feedback;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record FeedbackUpdateRequest(
        @NotBlank(message = "A descrição não pode ser nula ou vazia.")
        @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres.")
        String description,

        @NotNull(message = "A avaliação não pode ser nula.")
        @Min(value = 0, message = "A avaliação deve ser no mínimo 0.")
        @Max(value = 5, message = "A avaliação deve ser no máximo 5.")
        Integer rating,

        @NotBlank(message = "O ID do carrinho não pode ser nulo ou vazio.")
        String cartId,

        @NotNull(message = "O ID do usuário não pode ser nulo.")
        Long userId
) {}
