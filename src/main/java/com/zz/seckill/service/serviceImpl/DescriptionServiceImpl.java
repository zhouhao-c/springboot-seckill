package com.zz.seckill.service.serviceImpl;

import com.zz.seckill.Dao.DescriptionDao;
import com.zz.seckill.Dao.mapper.DescriptionMapper;
import com.zz.seckill.bean.Description;
import com.zz.seckill.service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private DescriptionDao descriptionDao;
    @Autowired
    private DescriptionMapper descriptionMapper;

    @Override
    public void insertDescription(Description description) {
        descriptionDao.save(description);
    }

    @Override
    public int queryId() {
        return descriptionMapper.queryId();
    }

    @Override
    public Description queryById(int descriptionId) {
        return descriptionMapper.queryById(descriptionId);
    }
}
