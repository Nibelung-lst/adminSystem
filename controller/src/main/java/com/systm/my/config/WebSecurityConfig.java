package com.systm.my.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.my.base.utils.Result;
import com.system.my.base.utils.ResultUtil;
import com.system.my.serviceimpl.hr.HrServiceImpl;
import com.systm.my.utils.MyLoginFailucreHandler;
import com.systm.my.utils.MyLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:33
 * @describe
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    HrServiceImpl hrService;
    @Resource
    MyLoginSuccessHandler myLoginSuccessHandler;
    @Resource
    MyLoginFailucreHandler myLoginFailucreHandler;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /** 将JWT拦截器添加到获取用户信息拦截器之前*/
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter=new JwtAuthenticationTokenFilter();
        http.addFilterBefore(jwtAuthenticationTokenFilter,CustomAuthenticationFilter.class);

               http.formLogin()
                .loginProcessingUrl("/login")
                       .successHandler(myLoginSuccessHandler)
                       .failureHandler(myLoginFailucreHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(ResultUtil.success("注销成功!")));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint(){
                    @Override
                    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        resp.setStatus(401);
                        PrintWriter out = resp.getWriter();
                        Result respBean = ResultUtil.error(401,"访问失败!");
                        if (authException instanceof InsufficientAuthenticationException) {
                            respBean.setMsg("需要相应的控制权限，请检查是否登录或者有权限");
                        }
                        out.write(new ObjectMapper().writeValueAsString(respBean));
                        out.flush();
                        out.close();
                    }
                });
        http.csrf().disable().cors().and().authorizeRequests()
                .anyRequest()
                .access("@rpcService.hasPermission(request,authentication)");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception{
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myLoginSuccessHandler);
        filter.setAuthenticationFailureHandler(myLoginFailucreHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}
