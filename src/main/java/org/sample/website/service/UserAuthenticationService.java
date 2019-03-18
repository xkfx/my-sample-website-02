package org.sample.website.service;

import org.sample.website.model.User;

public interface UserAuthenticationService {

    String login(String username, String pwd);

    void verifyAndSetCurrentUser(String token);

    User getCurrentUser();
}
