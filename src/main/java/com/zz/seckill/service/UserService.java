package com.zz.seckill.service;


import com.zz.seckill.bean.User;

public interface UserService {
    User findByUserName(String userName);

    void saveOperateLog(User user, String remoteAddr);
}
