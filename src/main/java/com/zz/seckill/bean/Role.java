package com.zz.seckill.bean;

import com.zz.seckill.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_roles_resources", joinColumns = {@JoinColumn(name = "rid")}, inverseJoinColumns = {@JoinColumn(name = "eid")})
    private Set<WebResource> resources;
}
