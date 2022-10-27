package com.example.demo.Moduls.Response;

import com.example.demo.Moduls.User.AppUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessResponse {
    private static AccessResponse accessResponse;
    private String AccessToken;
    private String RefreshToken;
    private AppUser user;

    public static AccessResponse self(){
        return accessResponse;
    }
    public void setUser(AppUser user){
        accessResponse.user = user;
    }
    public void setTokens(String accessToken , String refreshToken){
        accessResponse.AccessToken = accessToken ;
        accessResponse.RefreshToken = refreshToken;
    }

}