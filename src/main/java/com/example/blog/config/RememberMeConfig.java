package com.example.blog.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

@Component
public class RememberMeConfig {

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        // repo.setCreateTableOnStartup(true);
        return repo;
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService uds, PersistentTokenRepository repo) {
        var svc = new PersistentTokenBasedRememberMeServices("SecretKey123", uds, repo);
        svc.setTokenValiditySeconds(7 * 24 * 60 * 60);
        svc.setParameter("rememberMe");
        // For localhost HTTP:
        svc.setUseSecureCookie(false);
        return svc;
    }
}
