package com.zz.seckill.Dao.mapper;

import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    String queryTelephoneByName(String userName);
}
