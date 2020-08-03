package com.zz.seckill.service.serviceImpl;

import com.zz.seckill.Dao.GoodsDao;
import com.zz.seckill.Dao.mapper.GoodsMapper;
import com.zz.seckill.bean.Goods;
import com.zz.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int queryPageCount(Map<String, Object> paramMap) {
        return goodsMapper.queryPageCount(paramMap);
    }

    @Override
    public List<Goods> queryPageData(Map<String, Object> paramMap) {
        return goodsMapper.queryPageData(paramMap);
    }

    @Override
    public void insertGoodsCategory(Goods goods) {
        goodsDao.save(goods);
    }

    @Override
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    @Override
    public Goods queryById(Integer id) {
        return goodsMapper.queryById(id);
    }


}
