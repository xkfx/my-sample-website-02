package org.sample.website.web;

import org.sample.website.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/tokens")
public class TokenController {

    private final UserAuthenticationService authenticationService;

    @Autowired
    public TokenController(UserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<?> getToken(String username, String pwd) {
        String token = authenticationService.login(username, pwd);
        System.out.println("token should be: " + token);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
