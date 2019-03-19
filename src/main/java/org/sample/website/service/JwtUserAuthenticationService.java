package org.sample.website.service;

import org.sample.website.model.User;

public interface JwtUserAuthenticationService {

    void register(User user);

    String login(String username, String pwd);

    void verifyAndSetCurrentUser(String token);

    User getCurrentUser();
}
