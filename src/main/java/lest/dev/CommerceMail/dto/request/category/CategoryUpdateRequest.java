package lest.dev.CommerceMail.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record CategoryUpdateRequest(

    @NotNull(message = "O ID da categoria é obrigatório")
    Long id,

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    String name
) {} 