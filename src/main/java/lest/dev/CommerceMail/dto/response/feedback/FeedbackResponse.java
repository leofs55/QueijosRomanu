package lest.dev.CommerceMail.dto.response.feedback;

import lest.dev.CommerceMail.dto.response.user.UserResponse;
import lombok.Builder;

@Builder
public record FeedbackResponse(Long id,
                               String description,
                               Integer rating,
                               String cartId,
                               UserResponse userResponse) {
}
