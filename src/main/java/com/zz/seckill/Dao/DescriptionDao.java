package com.zz.seckill.Dao;

import com.zz.seckill.bean.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DescriptionDao extends JpaRepository<Description,Long> {

}
