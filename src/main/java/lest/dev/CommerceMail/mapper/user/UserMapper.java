package lest.dev.CommerceMail.mapper.user;

import lest.dev.CommerceMail.dto.response.user.UserResponse;
import lest.dev.CommerceMail.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static UserResponse map(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .numberPhone(user.getPhoneNumber())
                .build();
    }
}
