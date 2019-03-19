package org.sample.website.web;

import org.sample.website.service.JwtUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/tokens")
public class TokenController {

    private final JwtUserAuthenticationService authenticationService;

    @Autowired
    public TokenController(JwtUserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<?> getToken(String username, String pwd) {
        String token = authenticationService.login(username, pwd);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
