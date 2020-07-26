package com.zz.seckill.Dao;

import com.zz.seckill.bean.WebResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebResourceDao extends JpaRepository<WebResource,Long> {
    List<WebResource> findAll();
}
