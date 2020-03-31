package com.system.my.mapper.roleMapper;

import com.system.my.base.admin.dto.Menu;
import com.system.my.base.admin.dto.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:21
 * @describe
 **/
public interface RoleMapper {
    /**
     * a
     * @return
     */
    List<Role> getAllRole();

    /**
     *
     * @param menuId
     * @return
     */
    List<Role> getRoleByMenuId(@Param("menuId") Integer menuId);

    /**
     *
     * @param roleName
     * @return
     */
    List<Role> searchRoleByNameZh(@Param("roleName")String roleName);

    /**
     *
     * @param menuId
     * @param roleId
     */
    void addAuthority(@Param("menuId")Integer menuId,@Param("roleId")Integer roleId);

    /**
     *
     * @param adminId
     * @return
     */
    Integer getRoleIdByAdminId(@Param("adminId")Integer adminId);
    /**
     *
     * @param adminId
     * @param roleId
     */
    void addRoleAndAdmin(@Param("adminId") Integer adminId,@Param("roleId")Integer roleId);

    /**
     *
     * @param adminId
     * @param roleId
     */
    void updateRoleAndAdmin(@Param("adminId") Integer adminId,@Param("roleId")Integer roleId);

    /**
     *
     * @param name
     * @param nameZh
     */
    void addRole(@Param("name")String name,@Param("nameZh")String nameZh);
}
