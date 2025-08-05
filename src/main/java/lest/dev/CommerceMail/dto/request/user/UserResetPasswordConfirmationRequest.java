package lest.dev.CommerceMail.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserResetPasswordConfirmationRequest(@NotBlank(message = "O e-mail do usuário é obrigatório")
                                       @Email(message = "O e-mail informado não é válido")
                                       String email) {
}
