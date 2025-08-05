package lest.dev.CommerceMail.mapper.user;

import lest.dev.CommerceMail.dto.request.user.UserUpdateRequest;
import lest.dev.CommerceMail.dto.response.user.UserUpdateReponse;
import lest.dev.CommerceMail.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUpdateMapper {

    public static User map(UserUpdateRequest userUpdateReponse) {
        return User.builder()
                .name(userUpdateReponse.name())
                .email(userUpdateReponse.email())
                .phoneNumber(userUpdateReponse.numberPhone())
                .password(userUpdateReponse.password())
                .build();
    }

    public static UserUpdateReponse map(User user) {
        return UserUpdateReponse.builder()
                .id(user.getId())
                .name(user.getName())
                .numberPhone(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }

}
