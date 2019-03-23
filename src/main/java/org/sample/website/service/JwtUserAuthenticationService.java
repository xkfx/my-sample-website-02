package org.sample.website.service;

import org.sample.website.model.User;

public interface JwtUserAuthenticationService {

    void register(User user);

    String login(String username, String pwd);

    void verifyAndSetCurrentUser(String token);

    User getCurrentUser();

    /**
     * 必须要手动调用，否则线程重用会出现
     * 不好的结果，例如获取到别人的用户信息。
     * @return
     */
    void removeCurrentUser();
}
