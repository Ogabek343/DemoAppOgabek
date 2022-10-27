package com.example.demo.Service;

import com.example.demo.Moduls.Response.AccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class ApiResponseService {
    private final UserService userService;
    private AccessResponse accessResponse = new AccessResponse();
    public AccessResponse getAll(HttpServletRequest request) {
        accessResponse.setUser(userService.getUser(request.getParameter("username")));
        return accessResponse;
    }
    public String refreshToken(HttpServletRequest request , HttpServletResponse response){
        return "";
    }

}
