package com.zz.seckill.Dao.mapper;

import com.zz.seckill.bean.Goods;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface GoodsMapper {
    int queryPageCount(Map<String, Object> paramMap);
    List<Goods> queryPageData(Map<String, Object> paramMap);
}
