package com.system.my.serviceimpl.hr;

import com.system.my.base.admin.dto.Menu;
import com.system.my.mapper.menuMapper.MenuMapper;
import com.system.my.service.hr.MenuService;
import com.system.my.utils.LayTreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:20
 * @describe
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public Boolean checkMenuName(String menuName) {
        return menuMapper.checkMenuName(menuName);
    }

    @Override
    public Boolean checkMenuUrl(String menuUrl) {
        return menuMapper.checkMenuUrl(menuUrl);
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menuList = menuMapper.getAllMenu();
        return LayTreeUtils.pushManyGroup(menuList,0);
    }

    @Override
    public Boolean addMenu(Menu menu) {
        menu.setEnabled(1);
        if (menu.getParentId() == null){
            menu.setParentId(0);
        }
        menuMapper.addMenu(menu);
        return true;
    }


}
