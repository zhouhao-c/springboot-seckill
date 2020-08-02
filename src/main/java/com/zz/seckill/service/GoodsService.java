package com.zz.seckill.service;

import com.zz.seckill.bean.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    int queryPageCount(Map<String, Object> paramMap);

    List<Goods> queryPageData(Map<String, Object> paramMap);

    void insertGoodsCategory(Goods goods);
}