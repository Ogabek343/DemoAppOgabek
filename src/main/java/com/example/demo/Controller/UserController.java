package com.example.demo.Controller;

import com.example.demo.Moduls.Response.AccessResponse;
import com.example.demo.Service.ApiResponseService;
import com.example.demo.Moduls.User.AppUser;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final ApiResponseService responseService;

    @GetMapping("/user/all")
    public AccessResponse getAll(HttpServletRequest  request){
        return responseService.getAll(request);
    }
    @GetMapping("/user/get/{id}")
    public AppUser getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
    @PutMapping("/user/update/")
    public void updateUser(@RequestBody AppUser user){
        userService.updateUser( user);
    }

}
