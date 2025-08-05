package lest.dev.CommerceMail.dto.response.user;

import lombok.Builder;

@Builder
public record UserUpdateReponse(Long id,
                                String name,
                                String email,
                                String numberPhone) {
}
