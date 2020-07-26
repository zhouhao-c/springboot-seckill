package com.zz.seckill.service;

import com.zz.seckill.bean.Role;
import com.zz.seckill.bean.WebResource;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private WebResourceService webResourceService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //取得用户
        com.zz.seckill.bean.User dbuser = userService.findByUserName(userName);
        if (dbuser == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        // 取得用户的权限
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(dbuser);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : dbuser.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        // 封装成spring security的user
        User userDetail = new User(dbuser.getUserName(), dbuser.getPassword(),
                true,//是否可用
                true,//是否过期
                true,//证书不过期为true
                true,//账户未锁定为true ,
                grantedAuths);
        return userDetail;
    }

    // 取得用户的权限
    private Set<GrantedAuthority> obtionGrantedAuthorities(com.zz.seckill.bean.User user) {
        List<WebResource> resources = new ArrayList<WebResource>();
        //获取用户的角色
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            Set<WebResource> res = role.getResources();
            for (WebResource resource : res) {
                resources.add(resource);
            }
        }
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        for (WebResource r : resources) {
            //用户可以访问的资源名称（或者说用户所拥有的权限）
            authSet.add(new SimpleGrantedAuthority(r.getValue()));
        }
        return authSet;
    }
}
