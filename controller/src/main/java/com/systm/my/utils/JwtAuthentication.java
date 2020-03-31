package com.systm.my.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

/**
 * @author shitao.liu
 * @create 2019-11-09 10:02
 * {@link org.springframework.security.core.Authentication}及其实现类的大部分属性没有提供setter方法，
 *  所以在通过json转换回Authentication时，没有setter方法的属性就赋值为空，此类是json转换回Authentication的中间类。
 */
public  class JwtAuthentication implements Authentication {

    //用户权限集合 => 可用于访问受保护资源时的权限验证

    private Collection<SimpleGrantedAuthority> authorities;

    //细节

    private Object details;

    //    是否已认证成功

    private boolean authenticated;

    // ①Principal（准则）=> 允许通过的规则，即允许访问的规则，基本等价于UserDetails（用户信息）

    private Object principal;

    //    用户证书（密码） => 初次认证的时候，进行填充，认证成功后将被清空

    private Object credentials;

    private String name;



    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return  name;
    }
}
