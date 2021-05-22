package com.lenoox.promusic.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                anonymous().disable()
                .authorizeRequests()

                .mvcMatchers(HttpMethod.GET,"/brand/{id}","/category/{id}","/status/{id}").denyAll()
                .mvcMatchers(HttpMethod.POST,"/brand","/category","/status").denyAll()
                .mvcMatchers(HttpMethod.PUT,"/brand/{id}","/category/{id}","/status/{id}").denyAll()
                .mvcMatchers(HttpMethod.DELETE,"/brand/{id}","/category/{id}","/status/{id}").denyAll()

                .mvcMatchers(HttpMethod.POST,"/order").hasRole("USER")
                .mvcMatchers(HttpMethod.GET,"/order","/order/{id}").hasRole("EMPLOYEE")
                .mvcMatchers(HttpMethod.PUT,"/order/{id}/status").hasRole("EMPLOYEE")
                .mvcMatchers(HttpMethod.DELETE,"/order/{id}").denyAll()

                .mvcMatchers(HttpMethod.GET,"/product").hasRole("EMPLOYEE")
                .mvcMatchers(HttpMethod.POST,"/product").hasRole("EMPLOYEE")
                .mvcMatchers(HttpMethod.PUT,"/product/{id}").hasRole("EMPLOYEE")
                .mvcMatchers(HttpMethod.DELETE,"/product/{id}").denyAll()

                .mvcMatchers("/users/me","/users/me/password").hasAnyRole("EMPLOYEE","USER")
                .mvcMatchers(HttpMethod.DELETE,"/users/{id}").denyAll()
                .mvcMatchers(HttpMethod.GET,"/users").denyAll()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
