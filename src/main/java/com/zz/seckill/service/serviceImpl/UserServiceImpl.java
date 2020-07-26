package com.zz.seckill.service.serviceImpl;

import com.zz.seckill.Dao.UserDao;
import com.zz.seckill.bean.User;
import com.zz.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
