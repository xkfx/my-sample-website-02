package org.sample.website.service.impl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.sample.website.exception.AuthenticationException;
import org.sample.website.model.User;
import org.sample.website.service.UserAuthenticationService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    /**
     * 临时数据
     */
    private static final Long ID = 1000L;
    private static final String USER_NAME = "123";
    private static final String PWD = "123";

    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    @Override
    public String login(String username, String pwd) {
        if (USER_NAME.equals(username) && PWD.equals(pwd)) {
            return Jwts.builder()
                    .setSubject(String.valueOf(ID))
                    .setExpiration(new Date())
                    .signWith(KEY)
                    .compact();
        } else {
            throw new AuthenticationException("用户名或者密码不存在");
        }
    }

    @Override
    public void verifyAndSetCurrentUser(String token) {
        try {
            String id = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody().getSubject();
            userThreadLocal.set(new User(Long.parseLong(id), USER_NAME, PWD));
        }catch (JwtException e) {
            throw e;
        }
    }

    @Override
    public User getCurrentUser() {
        User user = userThreadLocal.get();
        return user != null ? user : new User(-1L, "游客", "");
    }
}
