package com.zz.seckill.config;

import org.hibernate.dialect.MySQL5Dialect;


public class MySqlConfig extends MySQL5Dialect  {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
