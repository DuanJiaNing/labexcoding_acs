package com.acs.admin.config;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    DefaultWebSecurityManager securityManager(ACSRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        return manager;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // swagger 文档相关的接口允许匿名访问
        chainDefinition.addPathDefinition("/swagger**", "anon");
        chainDefinition.addPathDefinition("/webjars**", "anon");

        // 登录接口允许匿名访问
        chainDefinition.addPathDefinition("/api/login", "anon");

        // 所有 api 开头的接口都需要登录
        chainDefinition.addPathDefinition("/api/**", "authc");
        return chainDefinition;
    }
}
