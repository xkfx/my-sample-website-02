package org.sample.website.web;

import org.sample.website.model.User;
import org.sample.website.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final UserAuthenticationService authenticationService;

    @Autowired
    public UserController(UserAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<?> getUser() {
        User user = authenticationService.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
