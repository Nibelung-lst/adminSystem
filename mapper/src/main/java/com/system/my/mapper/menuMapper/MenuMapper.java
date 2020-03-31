package com.system.my.mapper.menuMapper;

import com.system.my.base.admin.dto.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/24 11:21
 * @describe
 **/
public interface MenuMapper {
    /**
     * a
     * @return
     */
    List<Menu> getAllMenusWithRole();

    /**
     *
     * @return
     */
    Boolean checkMenuName(@Param("name") String menuName);

    /**
     *
     * @param menuUrl
     * @return
     */
    Boolean checkMenuUrl(@Param("url") String menuUrl);

    /**
     *
     * @return
     */
    List<Menu> getAllMenu();

    /**
     *
     * @param menu
     */
    void addMenu(Menu menu);
}
