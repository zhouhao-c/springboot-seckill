package com.zz.seckill.service;

import com.zz.seckill.bean.OperatorInfo;
import org.springframework.stereotype.Service;

@Service
public interface OperatorInfoService {
    OperatorInfo findByUserName(String userName);
}
