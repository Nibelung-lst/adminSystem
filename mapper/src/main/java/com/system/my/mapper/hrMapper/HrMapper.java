package com.system.my.mapper.hrMapper;

import com.system.my.base.admin.dto.AdminDTO;
import com.system.my.base.admin.dto.Hr;
import com.system.my.base.admin.dto.Role;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:04
 * @describe 11
 **/
public interface HrMapper {

    List<AdminDTO> getAllUser();
    /**
     * as
     * @param username
     * @return as
     */
    Hr loadUserByUsername(String username);

    /**
     *
     * @param username
     * @return
     */
    List<Integer> getRolesByUserName(String username);

    /**
     * as
     * @param id
     * @return as
     */
    List<Role> getHrRolesById(Integer id);

    /**
     *
     * @param username
     * @return
     */
    Boolean checkAdmin(String username);

    /**
     *
     * @param adminDTO
     */
    void addAdmin(AdminDTO adminDTO);
}
