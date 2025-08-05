package lest.dev.CommerceMail.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotBlank(message = "O nome do usuário é obrigatório")
        String name,

        @NotBlank(message = "O e-mail do usuário é obrigatório")
        @Email(message = "O e-mail informado não é válido")
        String email,

        @NotBlank(message = "O número de celular não pode estar em branco.")
        @Pattern(regexp = "^\\d{10,11}$", message = "O número de celular deve conter 10 ou 11 dígitos (DDD + número).")
        String numberPhone,

        @NotBlank(message = "A senha não pode estar em branco.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        // Regex para: 8+ caracteres, pelo menos 1 maiúscula, 1 minúscula, 1 número, 1 caractere especial
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+={}\\[\\]|:;\"'<>,.?/~`]).{8,}$",
                message = "A senha deve conter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um caractere especial.")
        String password)
{}
