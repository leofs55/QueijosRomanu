package lest.dev.CommerceMail.usecase;

import lest.dev.CommerceMail.entity.Feedback;

import java.util.List;

public interface FeedbackUsecases {

    List<Feedback> findAllFeedbacks();

    Feedback createFeedback(Feedback feedback);

    boolean deleteFeedback(Long id);

    Feedback updateFeedback(Feedback feedback);

    Feedback findFeedbackByCartId(String id);

    Feedback findFeedbackById(Long id);

}
