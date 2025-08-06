package lest.dev.CommerceMail.mapper.feedback;

import lest.dev.CommerceMail.dto.request.feedback.FeedbackUpdateRequest;
import lest.dev.CommerceMail.dto.response.feedback.FeedbackUpdateResponse;
import lest.dev.CommerceMail.entity.Feedback;
import lest.dev.CommerceMail.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedbackUpdateMapper {

    public static Feedback map(FeedbackUpdateRequest feedbackUpdateRequest) {
        return Feedback.builder()
                .description(feedbackUpdateRequest.description())
                .rating(feedbackUpdateRequest.rating())
                .cartId(feedbackUpdateRequest.cartId())
                .userId(feedbackUpdateRequest.userId())
                .build();
    }

    public static FeedbackUpdateResponse map(Feedback feedback) {
        return FeedbackUpdateResponse.builder()
                .id(feedback.getId())
                .description(feedback.getDescription())
                .rating(feedback.getRating())
                .build();
    }
}
