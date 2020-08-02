package com.zz.seckill.Dao;

import com.zz.seckill.bean.WebResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WebResourceDao extends JpaRepository<WebResource,Long> {
    List<WebResource> findAll();
}
