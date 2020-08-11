package com.zz.seckill.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 待秒杀商品表
 */
@Entity
@Table(name = "tb_goods")
public class Goods implements Serializable {
    private static final long serialVersionUID = 4192966038347855596L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int stock;
    private String name;
    private String number;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "endTime")
    private String endTime;
    @Column(name = "createTime")
    private String createTime;

    @Version
    private int version;
    @Transient
    private int descriptionId;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "description_id", referencedColumnName = "id")
    private Description description;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(int descriptionId) {
        this.descriptionId = descriptionId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
