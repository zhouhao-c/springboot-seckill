package com.zz.seckill.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorInfo {
    private String name;
    private Set<Role> roles;
    private String userName;
    private String password;

}
