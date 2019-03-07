package org.sample.website.service;

import org.sample.website.model.User;

public interface UserAuthenticationService {

    User getSubject(String uid, String pwd);

    User getSubject(String token);


}
