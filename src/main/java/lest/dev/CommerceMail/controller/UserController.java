package lest.dev.CommerceMail.controller;

import jakarta.validation.Valid;
import lest.dev.CommerceMail.config.TokenService;
import lest.dev.CommerceMail.dto.request.user.*;
import lest.dev.CommerceMail.dto.response.user.*;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.exception.User.UsernameOrPasswordInvalidException;
import lest.dev.CommerceMail.mapper.user.UserCreateMapper;
import lest.dev.CommerceMail.mapper.user.UserMapper;
import lest.dev.CommerceMail.mapper.user.UserUpdateMapper;
import lest.dev.CommerceMail.service.EmailService;
import lest.dev.CommerceMail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<UserCreateReponse> createUserEndPoint(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        UserCreateReponse response = UserCreateMapper.map(userService.createUser(UserCreateMapper.map(userCreateRequest)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserUpdateReponse> updateUserEndPoint(@PathVariable Long id,
                                                                @Valid @RequestBody UserUpdateRequest userUpdateRequest,
                                                                @AuthenticationPrincipal JWTUser user) {

        userService.validateUserAccess(id, user);

        UserUpdateReponse response = UserUpdateMapper.map(
                userService.updateUser(id, UserUpdateMapper.map(userUpdateRequest))
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> logingUserEndPoint(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            UsernamePasswordAuthenticationToken UsernameAndPassword = new UsernamePasswordAuthenticationToken(userLoginRequest.email(), userLoginRequest.password());
            Authentication authentication = authenticationManager.authenticate(UsernameAndPassword);

            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok(UserLoginResponse.builder()
                            .id(user.getId())
                            .role(user.getUserRole())
                            .token(tokenService.generateToken(user))
                            .build());
        } catch (BadCredentialsException ex) {
            throw new UsernameOrPasswordInvalidException("Usuário ou senha invalido!");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserEndPoint(@Valid @PathVariable Long id,
                                                         @AuthenticationPrincipal JWTUser user) {

        userService.validateUserAccess(id, user);

        UserResponse userResponse = UserMapper.map(
            userService.findUserById(id)
        );
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> sendEmailForResetPassword(@Valid
                                                            @RequestBody UserResetPasswordConfirmationRequest userResetPasswordRequest) {

        User user = userService.findUserByEmail(userResetPasswordRequest.email());

        try {
            emailService.sendEmail(userResetPasswordRequest.email(), user.getId());
            return ResponseEntity.ok("Email enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.ok("Erro ao enviar o email!!");
        }
    }

    @PostMapping("/reset-password/{userId}")
    public ResponseEntity<String> sendPasswordForReset(@Valid
                                                       @RequestBody
                                                       UserResetPasswordRequest userResetPasswordRequest,
                                                       @PathVariable Long userId) {
        try {
            userService.resetPasswordOfUser(userId, userResetPasswordRequest.password());
            return ResponseEntity.ok("Senha salva com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.ok("Erro ao salvar a senha!");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> findAllUsersEndPoint(@AuthenticationPrincipal JWTUser jwtUser) {
        userService.validatePermissionUser(jwtUser);
        List<UserResponse> userList = userService.findAllUsers().stream()
                .map(UserMapper::map)
                .toList();
        return ResponseEntity.ok(userList);
    }
}
