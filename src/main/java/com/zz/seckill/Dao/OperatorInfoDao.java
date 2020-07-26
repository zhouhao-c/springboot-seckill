package com.zz.seckill.Dao;

import com.zz.seckill.bean.OperatorInfo;
import com.zz.seckill.bean.User;

public interface OperatorInfoDao{
    OperatorInfo findByUserName(String userName);

    void saveOperateLog(User user, String remoteAddr);
}
