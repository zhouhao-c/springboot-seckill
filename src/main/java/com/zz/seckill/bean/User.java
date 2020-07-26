package com.zz.seckill.bean;

import com.zz.seckill.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "oder_id", length = 32)
    private String oderId;
    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 密码
     */
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private String remoteAddr;

}
