package com.example.phone_validation.controller;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/phone")
@Validated
public class StringChecker {

private static final List<Pattern> phoneNumberPatterns = List.of(
    Pattern.compile("^\\+7 \\d{3} \\d{3} \\d{4}$"),//+7 code ### ####
    Pattern.compile("^\\+7 \\(\\d{3}\\) \\d{3} \\d{4}$"),//+7 (code) ### ####
    Pattern.compile("^\\+7\\d{10}$"),//+7code#######
    Pattern.compile("^7\\d{10}$"),//7code####### (Russian number without +)
    Pattern.compile("^8\\(\\d{3}\\)\\d{3}-\\d{4}$"),//8(code)###-####
    Pattern.compile("^8\\(\\d{3}\\) \\d{3}-\\d{4}$"),//8(code) ###-####
    Pattern.compile("^8\\d{10}$"));//8code#######

@GetMapping("/validate")
    public ResponseEntity<Map<String, String>> ValidateMyPhoneNumber(@Validated @RequestParam("string") String request) {
        System.out.println("Input: '" + request + "'");
        System.out.println("Length: " + request.length());
        boolean isValid = phoneNumberPatterns.stream().anyMatch(pattern -> pattern.matcher(request).matches());
            if (isValid) {
                return ResponseEntity.ok(Map.of("result", "valid"));
            } else {
                return ResponseEntity.ok(Map.of("result", "invalid"));
            }
        
    }
}