package com.example.demo.Service;

import com.example.demo.Moduls.Token.ConfirmationToken;
import com.example.demo.Repository.TokenRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TokenService {
    private final TokenRepo tokenRepo;

    public void saveToken(ConfirmationToken token){
        tokenRepo.save(token);
    }

    public Optional<ConfirmationToken> findToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public void setConfirmed(String token) {
        ConfirmationToken confirmationToken = tokenRepo.findByToken(token).get();
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        tokenRepo.save(confirmationToken);
    }
}
