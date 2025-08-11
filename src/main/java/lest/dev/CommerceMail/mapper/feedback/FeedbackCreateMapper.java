package lest.dev.CommerceMail.mapper.feedback;

import lest.dev.CommerceMail.dto.request.feedback.FeedbackCreateRequest;
import lest.dev.CommerceMail.dto.response.feedback.FeedbackCreateResponse;
import lest.dev.CommerceMail.entity.Feedback;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedbackCreateMapper {

    public static Feedback map(FeedbackCreateRequest feedbackCreateRequest) {
        return Feedback.builder()
                .description(feedbackCreateRequest.description())
                .rating(feedbackCreateRequest.rating())
                .cartId(feedbackCreateRequest.cartId())
                .userId(feedbackCreateRequest.userId())
                .build();
    }

    public static FeedbackCreateResponse map(Feedback feedback) {
        return FeedbackCreateResponse.builder()
                .id(feedback.getId())
                .description(feedback.getDescription())
                .rating(feedback.getRating())
                .build();
    }
}
