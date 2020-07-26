package com.zz.seckill.service;

import com.zz.seckill.bean.OperatorInfo;
import com.zz.seckill.bean.User;

public interface OperatorInfoService {
    OperatorInfo findByUserName(String userName);

    void saveOperateLog(User user, String remoteAddr);
}
