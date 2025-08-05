package lest.dev.CommerceMail.dto.response.feedback;

import lombok.Builder;

@Builder
public record FeedbackUpdateResponse(Long id,
                                     String description,
                                     Integer rating) {
}
