package com.zz.seckill.bean;

import com.zz.seckill.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "tb_web_resource")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodPath() {
        return methodPath;
    }

    public void setMethodPath(String methodPath) {
        this.methodPath = methodPath;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
