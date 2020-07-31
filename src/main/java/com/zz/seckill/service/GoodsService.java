package com.zz.seckill.service;

import com.zz.seckill.bean.Goods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface GoodsService {
    int queryPageCount(Map<String, Object> paramMap);

    List<Goods> queryPageData(Map<String, Object> paramMap);
}
