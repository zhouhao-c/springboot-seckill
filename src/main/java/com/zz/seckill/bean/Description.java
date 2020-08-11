package com.zz.seckill.bean;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

/**
 * 商品简介表 Goods一对一关联
 */
@Entity
@Table(name = "tb_description")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "iconpath")
    private String iconpath;

    //表示无需创建表字段
    @Transient
    private MultipartFile imgFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
