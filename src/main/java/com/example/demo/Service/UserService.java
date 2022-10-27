package com.example.demo.Service;

import com.example.demo.Moduls.Token.ConfirmationToken;
import com.example.demo.Twilio.SMS.SmsRequest;
import com.example.demo.Twilio.TwilioService;
import com.example.demo.Moduls.User.AppUser;
import com.example.demo.Repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final TwilioService twilioService;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("User not found "));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getAuthorities().forEach(authority->{
            authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername() , user.getPassword() , authorities);
    }
    public String signUp(AppUser user) {

        boolean userExists = userRepo.findByUsername(user.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("Username is already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepo.save(user);
        String token = UUID.randomUUID().toString().substring(1 , 5);
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token ,
                LocalDateTime.now() ,
                LocalDateTime.now().plusMinutes(15) ,
                user
        );
        tokenService.saveToken(confirmationToken);
        twilioService.sendSms(new SmsRequest("+998933500625", token
        ));
        return "We have sent password to your number ";
    }

    public void enableUser(String username) {
        AppUser user = userRepo.findByUsername(username).get();
        user.setEnabled(true);
        user.setLocked(true);
        userRepo.save(user);
    }

    public void updateUser( AppUser user) {
        AppUser user1 = new AppUser(user);
        userRepo.save(user1);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public AppUser getUser(String username) {
        return userRepo.findByUsername(username).get();
    }

    public AppUser getUserById(Long id) {
        return userRepo.findById(id).get();
    }
}
