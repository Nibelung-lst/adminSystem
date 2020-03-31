package com.system.my.serviceimpl.hr;

import com.system.my.base.admin.dto.Role;
import com.system.my.mapper.roleMapper.RoleMapper;
import com.system.my.service.hr.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/28 17:21
 * @describe
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    /**
     *
     * @return
     */
    @Override
    public List<Role> getAllRole() {
        return roleMapper.getAllRole();
    }

    /**
     *
     * @param menuId
     * @return
     */
    @Override
    public List<Role> getRoleByMenuId(Integer menuId) {
        return roleMapper.getRoleByMenuId(menuId);
    }

    /**
     *
     * @param roleName
     * @return
     */
    @Override
    public List<Role> getRoleByRoleName(String roleName) {
        return roleMapper.searchRoleByNameZh(roleName);
    }

    /**
     *
     * @param menuId
     * @param roleId
     * @return
     */
    @Override
    public Boolean addAuthority(Integer menuId, Integer roleId) {
        roleMapper.addAuthority(menuId,roleId);
        return true;
    }

    /**
     *
     * @param adminId
     * @param roleId
     * @return
     */
    @Override
    public Boolean givePosition(Integer adminId, Integer roleId) {
        Integer check = roleMapper.getRoleIdByAdminId(adminId);
        if (check != null){
            roleMapper.updateRoleAndAdmin(adminId,roleId);
        }else {
            roleMapper.addRoleAndAdmin(adminId,roleId);
        }
        return true;
    }

    @Override
    public Boolean addRole(String name, String nameZh) {
        roleMapper.addRole(name,nameZh);
        return true;
    }

}
