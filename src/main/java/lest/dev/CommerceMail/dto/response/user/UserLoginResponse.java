package lest.dev.CommerceMail.dto.response.user;

import lest.dev.CommerceMail.enums.UsersRoles;
import lombok.Builder;

@Builder
public record UserLoginResponse(Long id,
                                UsersRoles role,
                                String token) {
}
