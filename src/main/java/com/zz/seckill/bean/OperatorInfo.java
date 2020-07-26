package com.zz.seckill.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_operatorInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键
    private String name;
    private Set<Role> roles;
    private String userName;
    private String password;
    private User user;
    private String remoteAddr;
}
