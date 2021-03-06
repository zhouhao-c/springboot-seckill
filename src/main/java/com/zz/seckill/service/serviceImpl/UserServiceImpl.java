package com.zz.seckill.service.serviceImpl;

import com.zz.seckill.Dao.UserDao;
import com.zz.seckill.Dao.mapper.UserMapper;
import com.zz.seckill.bean.User;
import com.zz.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }

    @Override
    public void saveOperateLog(User user, String remoteAddr) {
        User dbuser =userDao.findByUserName(user.getUserName());
        dbuser.setRemoteAddr(remoteAddr);
        userDao.save(dbuser);
    }


}
