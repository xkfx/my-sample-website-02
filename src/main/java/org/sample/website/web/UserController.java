package org.sample.website.web;

import org.sample.website.annotation.Authentication;
import org.sample.website.model.User;
import org.sample.website.service.JwtUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final JwtUserAuthenticationService authenticationService;

    @Autowired
    public UserController(JwtUserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Authentication
    @GetMapping
    public ResponseEntity<?> getUser() {
        User user = authenticationService.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveUser(User user) {
        authenticationService.register(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
