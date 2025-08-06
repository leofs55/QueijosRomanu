package lest.dev.CommerceMail.controller;

import jakarta.validation.Valid;
import lest.dev.CommerceMail.dto.request.feedback.FeedbackCreateRequest;
import lest.dev.CommerceMail.dto.request.feedback.FeedbackUpdateRequest;
import lest.dev.CommerceMail.dto.response.feedback.FeedbackCreateResponse;
import lest.dev.CommerceMail.dto.response.feedback.FeedbackDeleteResponse;
import lest.dev.CommerceMail.dto.response.feedback.FeedbackResponse;
import lest.dev.CommerceMail.dto.response.feedback.FeedbackUpdateResponse;
import lest.dev.CommerceMail.dto.response.user.JWTUser;
import lest.dev.CommerceMail.entity.Feedback;
import lest.dev.CommerceMail.mapper.feedback.FeedbackCreateMapper;
import lest.dev.CommerceMail.mapper.feedback.FeedbackDeleteMapper;
import lest.dev.CommerceMail.mapper.feedback.FeedbackMapper;
import lest.dev.CommerceMail.mapper.feedback.FeedbackUpdateMapper;
import lest.dev.CommerceMail.service.CartService;
import lest.dev.CommerceMail.service.FeedbackService;
import lest.dev.CommerceMail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackResponse>> findAllFeedbacksEndPoint() {
        List<FeedbackResponse> feedbackResponses =  feedbackService.findAllFeedbacks().stream()
                .map(feedback -> FeedbackMapper.map(feedback, userService.findUserById(feedback.getUserId())))
                .toList();
        return ResponseEntity.ok(feedbackResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<FeedbackCreateResponse> feedbackCreate(@Valid
                                                                 @RequestBody
                                                                 FeedbackCreateRequest feedbackCreateRequest,
                                                                 @AuthenticationPrincipal JWTUser jwtUser){

        userService.validateUserAccess(feedbackCreateRequest.userId(), jwtUser);
        cartService.validateUserOwnerCart(feedbackCreateRequest.cartId(), feedbackCreateRequest.userId());

        Feedback feedback = feedbackService.createFeedback(FeedbackCreateMapper.map(feedbackCreateRequest));
        FeedbackCreateResponse feedbackResponse = FeedbackCreateMapper.map(feedback);

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<FeedbackUpdateResponse> updateFeedback(@Valid
                                                                 @RequestBody
                                                                 FeedbackUpdateRequest feedbackRequest,
                                                                 @AuthenticationPrincipal JWTUser jwtUser){

        userService.validateUserAccess(feedbackRequest.userId(), jwtUser);
        cartService.validateUserOwnerCart(feedbackRequest.cartId(), feedbackRequest.userId());

        Feedback feedback = feedbackService.updateFeedback(FeedbackUpdateMapper.map(feedbackRequest));
        FeedbackUpdateResponse feedbackResponse = FeedbackUpdateMapper.map(feedback);

        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackResponse);

    }

    @GetMapping("/{cartId}")
    public ResponseEntity<FeedbackResponse> findFeedBack(@PathVariable String cartId){

        Feedback feedback = feedbackService.findFeedbackByCartId(cartId);
        FeedbackResponse feedbackResponse = FeedbackMapper.map(feedback, userService.findUserById(feedback.getUserId()));

        return ResponseEntity.status(HttpStatus.OK).body(feedbackResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<FeedbackDeleteResponse> deleteFeedback(@PathVariable
                                                                 Long id){

        FeedbackDeleteResponse feedbackResponse = FeedbackDeleteMapper.map(feedbackService.deleteFeedback(id));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(feedbackResponse);
    }
}
