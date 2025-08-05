package lest.dev.CommerceMail.dto.response.user;

import lombok.Builder;

@Builder
public record JWTUser(Long id, String email) {
}
