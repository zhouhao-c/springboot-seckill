package com.zz.seckill.service.serviceImpl;

import com.zz.seckill.Dao.OperatorInfoDao;
import com.zz.seckill.bean.OperatorInfo;
import com.zz.seckill.bean.User;
import com.zz.seckill.service.OperatorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorInfoServiceImpl implements OperatorInfoService {

    @Autowired
    private OperatorInfoDao operatorInfoDao;

    @Override
    public OperatorInfo findByUserName(String userName) {
        return operatorInfoDao.findByUserName(userName);
    }

    @Override
    public void saveOperateLog(User user, String remoteAddr) {
        operatorInfoDao.saveOperateLog(user,remoteAddr);
    }
}
