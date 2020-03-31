package com.system.my.serviceimpl.hr;

import com.system.my.base.admin.dto.AdminDTO;
import com.system.my.base.admin.dto.Hr;
import com.system.my.base.admin.dto.Role;
import com.system.my.mapper.hrMapper.HrMapper;
import com.system.my.service.hr.HrService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:01
 * @describe
 **/
@Service
public class HrServiceImpl implements HrService,UserDetailsService {
    @Resource
    HrMapper hrMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    @Override
    public List<Integer> getAdminByName(String username) {
        return hrMapper.getRolesByUserName(username);
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public Boolean checkAdmin(String username) {
        return hrMapper.checkAdmin(username);
    }

    /**
     * 添加管理员
     * @param adminDTO
     * @return
     */
    @Override
    public Boolean addAdmin(AdminDTO adminDTO) {
        try {
            adminDTO.setEnabled(1);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode("123");
            adminDTO.setPassword(password);
            adminDTO.setUsername(adminDTO.getEmail());
            hrMapper.addAdmin(adminDTO);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     *
     * @return
     */
    @Override
    public List<AdminDTO> getAllAdmin() {
        List<AdminDTO> adminList = hrMapper.getAllUser();
        for (AdminDTO adminDTO : adminList){
            Role role;
            List<Role> roleList = hrMapper.getHrRolesById(adminDTO.getId());
            if (!CollectionUtils.isEmpty(roleList)){
                role = roleList.get(0);
                adminDTO.setRole(role.getNameZh());
            }else {
                adminDTO.setRole("无");
            }
        }
        return adminList;
    }
}
