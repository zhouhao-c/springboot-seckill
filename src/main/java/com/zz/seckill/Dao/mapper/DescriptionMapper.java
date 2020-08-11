package com.zz.seckill.Dao.mapper;

import com.zz.seckill.bean.Description;
import org.springframework.stereotype.Component;

@Component
public interface DescriptionMapper {
    int queryId();

    Description queryById(int description_id);
}
