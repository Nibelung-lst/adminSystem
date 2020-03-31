package com.systm.my.config;

import com.systm.my.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT拦截器
 * @author wangru
 * @create 2019-11-09 9:48
 */

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取JWT token
        String authHeader = request.getHeader("Authorization");
        logger.info("--------->"+authHeader);
        if (authHeader != null) {
            try {
                JwtUtils.tokenParser(authHeader);
            }catch (Exception e){
                filterChain.doFilter(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}
