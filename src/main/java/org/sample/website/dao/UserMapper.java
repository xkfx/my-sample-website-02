package org.sample.website.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sample.website.model.User;

@Mapper
public interface UserMapper {

    void saveUser(User user);

    User getUser(@Param("username") String username,
                 @Param("pwd") String pwd);

    User getById(Long id);
}
