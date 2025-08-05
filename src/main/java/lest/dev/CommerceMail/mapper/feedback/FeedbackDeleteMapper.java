package lest.dev.CommerceMail.mapper.feedback;

import lest.dev.CommerceMail.dto.response.feedback.FeedbackDeleteResponse; 
import lombok.experimental.UtilityClass;

@UtilityClass
public class FeedbackDeleteMapper {

    public static FeedbackDeleteResponse map(Boolean resultado) {
        if (resultado) {
            return FeedbackDeleteResponse.builder()
                    .result("Feedback has deleted!")
                    .build();
        }
        return FeedbackDeleteResponse.builder()
                .result("Feedback could not be deleted!")
                .build();
    }

}
