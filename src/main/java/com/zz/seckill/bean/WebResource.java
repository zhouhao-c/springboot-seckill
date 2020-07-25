package com.zz.seckill.bean;

import com.zz.seckill.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ad_web_resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebResource extends BaseEntity{


    private static final long serialVersionUID = 2529504092312259660L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private String name; // 资源名称

    private String url;

    @Column(name="remark",length=200)
    private String remark;//备注

    @Column(name="methodName",length=400)
    private String methodName;//资源所对应的方法名

    @Column(name="methodPath",length=1000)
    private String methodPath;//资源所对应的包路径

    private String sn;

    private String value; // 资源标识
}
