package lest.dev.CommerceMail.service;

import lest.dev.CommerceMail.dto.response.user.JWTUser;
import lest.dev.CommerceMail.entity.User;
import lest.dev.CommerceMail.enums.UsersRoles;
import lest.dev.CommerceMail.exception.User.*;
import lest.dev.CommerceMail.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public User createUser(User user)
            throws IllegalArgumentException, UserCreationEmailException, UserCreationException {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null.");
        }
        try {
            if (repository.existsByEmail(user.getEmail())) {
                throw new UserCreationEmailException("This email already registered");
            }
            user.setUserRole(UsersRoles.valueOf("USER"));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return repository.save(user);

        } catch (UserCreationEmailException e) {

            throw new  UserCreationEmailException("This email already registered: "+ user.getEmail(), e);

        } catch (UserCreationException e) {

            throw new UserCreationException("Failed to create user: " + user.getEmail(), e);

        }
    }

    public User findUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    public User updateUser(Long id, User user) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null for update.");
        }
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null for update.");
        }

        User userInDb = findUserById(id);
        user.setId(userInDb.getId());
        user.setUserRole(userInDb.getUserRole());

        try {

            if (!passwordEncoder.matches(user.getPassword(), userInDb.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                user.setPassword(userInDb.getPassword());
            }

            return repository.save(user);
        } catch (Exception e) {
            throw new UserUpdateException(e.getLocalizedMessage(), e);
        }
    }

    public void validatePermissionUser(JWTUser jwtUser) {
        try {

            User user = findUserById(jwtUser.id());

            if (UsersRoles.USER == user.getUserRole()) {
                throw new PermissionDeniedUserAccessExcepetion("You don't have permission for access this feature!");
            }

        } catch (PermissionDeniedUserAccessExcepetion e) {

            throw new PermissionDeniedUserAccessExcepetion(e.getLocalizedMessage(), e);

        } catch (UserNotFoundException e) {

            throw new UserNotFoundException(e.getLocalizedMessage(), e);

        }
    }

    public void validateUserAccess(Long userId, JWTUser jwtUser){
        try {

            User user = findUserById(userId);

            User userInJwt = findUserById(jwtUser.id());

            if (!user.equals(userInJwt) && userInJwt.getUserRole() == UsersRoles.USER) {
                throw new UserAccessDennied("This user don't have permission for access ");
            }

        } catch (Exception e) {
            throw new UserAccessDennied(e.getLocalizedMessage(), e);
        }
    }

    public User findUserByEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null.");
        }
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with Email: " + email));
    }

    public void resetPasswordOfUser(Long userId, String password) {
        User user = findUserById(userId);
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }



}
