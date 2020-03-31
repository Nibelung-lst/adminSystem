package com.system.my.utils;

import com.system.my.base.admin.dto.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/3/28 16:41
 * @describe
 **/
public class LayTreeUtils {
    public static List<Menu> pushManyGroup(List<Menu> list, Integer pid){
        List<Menu> arr = new ArrayList<>();
        for (Menu location : list) {
            if(pid.equals(location.getParentId())){
                location.setChildren(pushManyGroup(list, location.getId()));
                arr.add(location);
            }
        }
        return arr;
    }
}
