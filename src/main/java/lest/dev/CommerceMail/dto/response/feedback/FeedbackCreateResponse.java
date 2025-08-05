package lest.dev.CommerceMail.dto.response.feedback;

import lombok.Builder;

@Builder
public record FeedbackCreateResponse(Long id,
                                     String description,
                                     Integer rating) {
}
