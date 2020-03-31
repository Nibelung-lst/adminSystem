package com.system.my.service.hr;

import com.system.my.base.admin.dto.Menu;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:16
 * @describe
 **/
public interface MenuService {
    /**
     * aa
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     *
     * @return
     */
    Boolean checkMenuName(String menuName);

    /**
     *
     * @return
     */
    Boolean checkMenuUrl(String menuUrl);

    /**
     *
     * @return
     */
    List<Menu> getAllMenu();

    /**
     *
     * @param menu
     * @return
     */
    Boolean addMenu(Menu menu);
}
