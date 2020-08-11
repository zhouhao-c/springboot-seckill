package com.zz.seckill.service;

import com.zz.seckill.bean.Description;

public interface DescriptionService {
    void insertDescription(Description description);

    int queryId();

    Description queryById(int descriptionId);
}
