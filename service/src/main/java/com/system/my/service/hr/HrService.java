package com.system.my.service.hr;

import com.system.my.base.admin.dto.AdminDTO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/26 12:19
 * @describe
 **/
public interface HrService {
    /**
     *
     * @param username
     * @return
     */
    List<Integer> getAdminByName(String username);

    /**
     * 查重
     * @param username
     * @return
     */
    Boolean checkAdmin(String username);

    /**
     * 添加管理员
     * @param adminDTO
     * @return
     */
    Boolean addAdmin(AdminDTO adminDTO);

    /**
     *
     * @return
     */
    List<AdminDTO> getAllAdmin();
}
