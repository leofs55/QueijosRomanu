package lest.dev.CommerceMail.mapper.user;

import lest.dev.CommerceMail.dto.request.user.UserCreateRequest;
import lest.dev.CommerceMail.dto.response.user.UserCreateReponse;
import lest.dev.CommerceMail.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserCreateMapper {

    public static User map(UserCreateRequest userCreateRequest) {
        return User.builder()
                .name(userCreateRequest.name())
                .email(userCreateRequest.email())
                .password(userCreateRequest.password())
                .phoneNumber(userCreateRequest.numberPhone())
                .userRole(userCreateRequest.userRole())
                .build();
    }

    public static UserCreateReponse map(User user) {
        return UserCreateReponse.builder()
                .name(user.getName())
                .build();
    }
}
