package com.systm.my.controller.admin;

import com.system.my.base.admin.dto.AdminDTO;
import com.system.my.base.admin.dto.Menu;
import com.system.my.base.admin.dto.Role;
import com.system.my.base.utils.Result;
import com.system.my.base.utils.ResultUtil;
import com.system.my.service.hr.HrService;
import com.system.my.service.hr.MenuService;
import com.system.my.service.hr.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/28 10:25
 * @describe
 **/
@RestController
@RequestMapping("admin")
public class AdminController {
    @Resource
    protected HrService hrService;
    @Resource
    protected MenuService menuService;
    @Resource
    private RoleService roleService;

    @CrossOrigin
    @RequestMapping("addAdmin")
    @ResponseBody
    public Result addAdmin(@RequestBody AdminDTO adminDTO){
        Boolean check = hrService.addAdmin(adminDTO);
        if (check){
            return ResultUtil.success("添加成功",true);
        }else {
            return ResultUtil.success("添加失败",false);
        }
    }

    @RequestMapping("checkAdmin")
    public Result checkAdmin(@RequestParam(value = "email")String username){
        Boolean check = hrService.checkAdmin(username);
        if (check){
            return ResultUtil.success("该邮箱已注册",true);
        }else {
            return ResultUtil.success("该邮箱可使用",false);
        }
    }

    /**
     *
     * @param menuName
     * @return
     */
   @RequestMapping("isExistedSystemName")
    public Result isExistedSystemName(@RequestParam(value = "name")String menuName){
        Boolean check = menuService.checkMenuName(menuName);
       if (check){
           return ResultUtil.success("该菜单名已存在",true);
       }else {
           return ResultUtil.success("该菜单名可以使用",false);
       }
   }

    /**
     *
     * @param url
     * @return
     */
    @RequestMapping("isExistedSystemUrl")
    public Result isExistedSystemUrl(@RequestParam(value = "url")String url){
        Boolean check = menuService.checkMenuUrl(url);
        if (check){
            return ResultUtil.success("该路由已存在",true);
        }else {
            return ResultUtil.success("该路由可以使用",false);
        }
    }

    /**
     *
     * @return
     */
   @RequestMapping("getAllMenu")
    public Result getAllMenu(){
       List<Menu> menuList = menuService.getAllMenu();
       return ResultUtil.success(menuList);
   }

    /**
     *
     * @return
     */
   @RequestMapping("getAllRole")
    public Result getAllRole(){
       List<Role> roleList = roleService.getAllRole();
       return ResultUtil.success(roleList);
   }

    /**
     *
     * @param mid
     * @return
     */
   @RequestMapping("getRoleByMid")
   public Result getRoleByMid(@RequestParam(value = "menuId")Integer mid){
       List<Role> roleList = roleService.getRoleByMenuId(mid);
       return ResultUtil.success(roleList);
   }

    /**
     *
     * @param name
     * @return
     */
   @RequestMapping("searchRole")
    public Result searchRole(@RequestParam(value = "roleName")String name){
       return ResultUtil.success(roleService.getRoleByRoleName(name));
   }

   @RequestMapping("addAuthority")
   public Result isExistedSystemName(@RequestParam(value = "menuId")Integer menuId,
                                     @RequestParam(value = "roleId") Integer roleId){
       Boolean check = roleService.addAuthority(menuId,roleId);
       if (check){
           return ResultUtil.success("分配成功",true);
       }else {
           return ResultUtil.success("分配失败",false);
       }
   }
   @RequestMapping("addMenu")
   @ResponseBody
    public Result addMenu(@RequestBody Menu menu){
       Boolean check = menuService.addMenu(menu);
       if (check){
           return ResultUtil.success("添加成功",true);
       }else {
           return ResultUtil.success("添加失败",false);
       }
   }

    /**
     *
     * @return
     */
   @RequestMapping("getAllAdmin")
   public Result getAllAdmin(){
       return ResultUtil.success(hrService.getAllAdmin());
   }

    /**
     *
     * @param adminId
     * @param roleId
     * @return
     */
   @RequestMapping("givePosition")
    public Result givePosition(@RequestParam(value = "adminId")Integer adminId,
                               @RequestParam(value = "roleId") Integer roleId){
       Boolean check = roleService.givePosition(adminId,roleId);
       if (check){
           return ResultUtil.success("添加成功",true);
       }else {
           return ResultUtil.success("添加失败",false);
       }
   }

   @RequestMapping("addRole")
    public Result addRole(@RequestParam(value = "name")String name,
                          @RequestParam(value = "nameZh") String nameZh){
       Boolean check = roleService.addRole(name,nameZh);
       if (check){
           return ResultUtil.success("添加成功",true);
       }else {
           return ResultUtil.success("添加失败",false);
       }
   }
}
