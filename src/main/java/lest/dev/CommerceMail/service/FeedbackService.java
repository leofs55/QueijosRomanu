package lest.dev.CommerceMail.service;

import lest.dev.CommerceMail.entity.Cart;
import lest.dev.CommerceMail.entity.Feedback;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.repository.FeedbackRepository;
import lest.dev.CommerceMail.usecase.FeedbackUsecases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService implements FeedbackUsecases {

    private final FeedbackRepository repository;
    private final UserService userService;
    private final CartService cartService;

    @Override
    public List<Feedback> findAllFeedbacks() {
        return repository.findAll();
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        try {
            if (feedback == null) {
                throw new IllegalArgumentException("Feedbak cannot be null.");
            }
            User user = userService.findUserById(feedback.getUser().getId());
            Cart cart = cartService.findCart(feedback.getCartId());
            if (user == null || cart == null) {
                throw new RuntimeException("UserId or CartId not exist!");
            }
            feedback.setUser(user);
            return repository.save(feedback);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }

    }

    @Override
    public boolean deleteFeedback(Long id) {
        boolean result = false;
        try {
            if (id == null) {
                throw new IllegalArgumentException("Feedbak id cannot be null.");
            }
            Feedback feedbackById = findFeedbackById(id);
            repository.delete(feedbackById);
            result = true;
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {
        try {
            if (feedback == null) { throw new IllegalArgumentException("Feedbak cannot be null."); }
            
            String cartId = feedback.getCartId();
            if (cartId == null || cartId.isEmpty()) { throw new IllegalArgumentException("Cart id cannot be null!"); }

            Feedback feedbackDb = findFeedbackByCartId(cartId);

            feedback.setId(feedbackDb.getId());
            feedback.setCartId(feedback.getCartId());
            User user = userService.findUserById(feedback.getUser().getId());
            feedback.setUser(user);

            return repository.save(feedback);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }


    }

    @Override
    public Feedback findFeedbackByCartId(String id) {
        try {
            return repository.findByCartId(id).orElseThrow(
                    () -> new RuntimeException("This cart don't have any feedbacks!")
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public Feedback findFeedbackById(Long id) {
        try {
            return repository.findById(id).orElseThrow(
                    () -> new RuntimeException("This cart don't have any feedbacks!")
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }


}
