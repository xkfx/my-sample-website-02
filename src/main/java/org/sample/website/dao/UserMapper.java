package org.sample.website.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sample.website.model.User;

@Mapper
public interface UserMapper {

    /**
     * 插入单条用户记录，用于支持“用户注册”功能
     * @param user 若插入成功，该user对象的id字段被设置为自动生成的主键
     */
    void saveUser(User user);

    /**
     * 根据用户名和密码查找用户，用于支持“用户登陆”功能
     * @param username 用户名
     * @param pwd 密码
     * @return 不存在则返回null
     */
    User getUser(@Param("username") String username,
                 @Param("pwd") String pwd);

    User getById(Long id);
}
