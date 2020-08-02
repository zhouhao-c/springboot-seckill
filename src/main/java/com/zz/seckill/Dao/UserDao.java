package com.zz.seckill.Dao;

import com.zz.seckill.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDao extends JpaRepository<User,String> {
    User findByUserName(String userName);
}
