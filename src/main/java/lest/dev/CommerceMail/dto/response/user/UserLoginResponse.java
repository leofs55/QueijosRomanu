package lest.dev.CommerceMail.dto.response.user;

import lombok.Builder;

@Builder
public record UserLoginResponse(Long id,
                                String token) {
}
