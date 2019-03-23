package org.sample.website.service.impl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.sample.website.dao.UserMapper;
import org.sample.website.exception.AuthenticationException;
import org.sample.website.model.User;
import org.sample.website.service.JwtUserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtUserAuthenticationServiceImpl implements JwtUserAuthenticationService {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    private final UserMapper userMapper;

    @Autowired
    public JwtUserAuthenticationServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void register(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public String login(String username, String pwd) {
        User user = userMapper.getUser(username, pwd);
        if (user != null) {
            return Jwts.builder()
                    .setSubject(String.valueOf(user.getId()))
//                    .setExpiration(new Date())
                    .signWith(SECRET_KEY)
                    .compact();
        } else {
            throw new AuthenticationException("用户名或者密码不存在");
        }
    }

    @Override
    public void verifyAndSetCurrentUser(String token) {
        try {
            String id = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            // 如果校验成功，执行以下代码
            User user = userMapper.getById(Long.valueOf(id));
            if (user != null) {
                userThreadLocal.set(user);
            } else {
                userThreadLocal.set(new User(-1L, "未登记用户", ""));
            }
        }catch (JwtException e) {
            throw new AuthenticationException(e);
        }
    }

    @Override
    public User getCurrentUser() {
        return userThreadLocal.get(); // 没经过校验的话，可能为空
    }

    /**
     * 必须要手动调用，否则线程重用会出现
     * 不好的结果，例如获取到别人的用户信息。
     *
     * @return
     */
    @Override
    public void removeCurrentUser() {
        userThreadLocal.remove();
    }
}
