package com.example.demo.Service;

import com.example.demo.Controller.NumberValidator;
import com.example.demo.Moduls.Request.RegistrationRequest;
import com.example.demo.Moduls.Token.ConfirmationToken;
import com.example.demo.Moduls.User.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.demo.Moduls.User.Role.USER;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final NumberValidator validator;
    private final UserService userService;
    private final TokenService tokenService;
    public String register(RegistrationRequest request) {
        boolean isValid = validator.test(request.getUsername());
        if(!isValid){
            throw new IllegalStateException("Username not valid");
        }
        return  userService.signUp(
                new AppUser(request.getUsername() ,
                        request.getPassword(),
                        request.getNumber(),
                        USER)
        );
    }
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = tokenService
                .findToken(token)
                .orElseThrow(()->new IllegalStateException("Token not found"));
        if(confirmationToken.getConfirmedAt()!=null){
            throw new IllegalStateException("Token already confirmed ");
        }
        if(confirmationToken.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Token expired");
        }
        tokenService.setConfirmed(token);
        userService.enableUser(
                confirmationToken.getUser().getUsername()
        );
        return "confirmed";
    }
}
