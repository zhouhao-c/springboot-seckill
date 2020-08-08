package com.zz.seckill.config.security;

import com.zz.seckill.Dao.WebResourceDao;
import com.zz.seckill.bean.WebResource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private org.slf4j.Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private WebResourceDao webResourceDao;

    /**
     * 加载资源，初始化资源变量
     */
    @PostConstruct
    public void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<WebResource> resources = webResourceDao.findAll();
            System.out.println(resources.toString());
            for (WebResource resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                ConfigAttribute configAttribute = new SecurityConfig(resource.getValue());
                configAttributes.add(configAttribute);
                resourceMap.put(resource.getUrl(), configAttributes);
            }
            System.out.println(resourceMap.toString());
        }
        LOG.info("security info load success!!");
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (resourceMap == null) loadResourceDefine();
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        // 返回当前 url  所需要的权限
        System.out.println(resourceMap.get(requestUrl));
        return resourceMap.get(requestUrl);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
