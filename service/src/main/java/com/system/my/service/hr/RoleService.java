package com.system.my.service.hr;

import com.system.my.base.admin.dto.Role;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/28 17:16
 * @describe
 **/
public interface RoleService {
    /**
     *
     * @return
     */
    List<Role> getAllRole();

    /**
     *
     * @param menuId
     * @return
     */
    List<Role> getRoleByMenuId(Integer menuId);

    /**
     *
     * @param
     * @return
     */
    List<Role> getRoleByRoleName(String roleName);

    /**
     *
     * @param menuId
     * @param roleId
     * @return
     */
    Boolean addAuthority(Integer menuId,Integer roleId);

    /**
     *
     * @param adminId
     * @param roleId
     * @return
     */
    Boolean givePosition(Integer adminId,Integer roleId);

    /**
     *
     * @param name
     * @param nameZh
     * @return
     */
    Boolean addRole(String name,String nameZh);
}
