package lest.dev.CommerceMail.mapper.feedback;

import lest.dev.CommerceMail.dto.response.feedback.FeedbackResponse;
import lest.dev.CommerceMail.entity.Feedback;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.mapper.user.UserMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedbackMapper {

    public static FeedbackResponse map(Feedback feedback, User user) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .description(feedback.getDescription())
                .rating(feedback.getRating())
                .cartId(feedback.getCartId())
                .userResponse(UserMapper.map(user))
                .build();
    }
}
