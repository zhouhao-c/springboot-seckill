package com.zz.seckill.Dao.mapper;

import com.zz.seckill.bean.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    User queryUserByName(String userName);
}
