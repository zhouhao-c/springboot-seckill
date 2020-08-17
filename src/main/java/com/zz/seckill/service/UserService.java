package com.zz.seckill.service;


import com.zz.seckill.bean.User;

public interface UserService {
    void saveOperateLog(User user, String remoteAddr);

    User findUserName(String userName);


    User queryUserByName(String userName);
}
