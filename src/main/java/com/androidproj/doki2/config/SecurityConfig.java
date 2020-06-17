package com.androidproj.doki2.config;

import com.androidproj.doki2.serviceImpl.MyUserDetailsService;
import com.androidproj.doki2.serviceImpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /*具体的权限控制规则配置。一个这个配置相当于xml配置中的一个标签。
各种具体的认证机制的相关配置*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/login/**","/user/register/**","/square/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login/signIn")
                .usernameParameter("username").passwordParameter("password")
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/fail")
        .and().csrf().disable();

        //配置URL安全规则
        //.authorizeRequests()
        //.antMatchers("/").permitAll()
        //.antMatchers("/user/register/**").permitAll()//默认不拦截注册路径
        //.anyRequest().authenticated()
        //.and()
//        http.authorizeRequests()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll()
//        .and().csrf().disable();
    }


    /*全局请求忽略规则配置
   （比如说静态文件，比如说注册页面）、全局HttpFirewall配置、是否debug配置、
   全局SecurityFilterChain配置、privilegeEvaluator、expressionHandler、securityInterceptor、*/
    public void configure(WebSecurity web) throws Exception {
    }

    /*AuthenticationManagerBuilder用来配置全局的认证相关的信息，
    其实就是AuthenticationProvider和UserDetailsService，
    前者是认证服务提供商，后者是用户详情查询服务。*/
    //完成用户认证
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

}
