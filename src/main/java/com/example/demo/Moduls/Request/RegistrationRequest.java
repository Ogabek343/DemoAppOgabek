package com.example.demo.Moduls.Request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
public class RegistrationRequest {
    private String username;
    private String number;
    private String password;
}
