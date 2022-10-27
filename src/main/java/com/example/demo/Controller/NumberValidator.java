package com.example.demo.Controller;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class NumberValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
