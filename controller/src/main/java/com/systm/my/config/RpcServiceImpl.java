package com.systm.my.config;

import com.system.my.base.admin.dto.Menu;
import com.system.my.base.admin.dto.Role;
import com.system.my.service.hr.HrService;
import com.system.my.service.hr.MenuService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 判断用户是否可以访问该路径(权限认证)
 * @author shitao.liu
 * @create 2019-11-16 15:07
 */

@Component("rpcService")
public class RpcServiceImpl implements RpcService,Serializable {

    /**
     * 调用远端服务
     */
    @Resource
    MenuService menuService;
    @Resource
    HrService hrService;

    /**
     * 匹配路径
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();



    /**
     *
     * @param request 请求头
     * @param authentication spring security 注入的用户身份
     * @return 是否可以访问该路径
     */
    @Override
    public Boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//        Boolean hasPerission=false;
//        String username = authentication.getName();
//        List<Integer> roleIds = hrService.getAdminByName(username);
//        if (CollectionUtils.isEmpty(roleIds)){
//            return false;
//        }
//        List<Menu> allMenu = menuService.getAllMenusWithRole();
//
//        for (Menu menu : allMenu) {
//            if (antPathMatcher.match(menu.getUrl(), request.getRequestURI())&&menu.getRoles().size()>0) {
//                List<Role> roles = menu.getRoles();
//
//                List<Integer> values = new ArrayList<>();
//                for (Role role : roles){
//                    values.add(role.getId());
//                }
//                for (Integer role:values){
//                    if (role.equals(roleIds.get(0))){
//                        hasPerission = true;
//                        break;
//                    }
//                }
//            }
//        }
//        return hasPerission;
        return true;
    }
}
