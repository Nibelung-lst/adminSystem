package com.system.my.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrossUtils {


  public static <T> List<List<T>> getDescartes(List<List<T>> list) {
    List<List<T>> returnList = new ArrayList<>();
    descartesRecursive(list, 0, returnList, new ArrayList<T>());
    return returnList;
  }

  /**
   * 递归实现
   * 原理：从原始list的0开始依次遍历到最后
   *
   * @param originalList 原始list
   * @param position     当前递归在原始list的position
   * @param returnList   返回结果
   * @param cacheList    临时保存的list
   */
  public static <T> void descartesRecursive(List<List<T>> originalList, int position, List<List<T>> returnList, List<T> cacheList) {
    List<T> originalItemList = originalList.get(position);
    for (int i = 0; i < originalItemList.size(); i++) {
      //最后一个复用cacheList，节省内存
      List<T> childCacheList = (i == originalItemList.size() - 1) ? cacheList : new ArrayList<>(cacheList);
      childCacheList.add(originalItemList.get(i));
      //遍历到最后退出递归
      if (position == originalList.size() - 1) {
        returnList.add(childCacheList);
        continue;
      }
      descartesRecursive(originalList, position + 1, returnList, childCacheList);
    }
  }

  /**
   *
   * @param lis
   * @param lis2
   * @return
   */
  public static Boolean CompareStringByChar(List<String> lis,List<String> lis2)
  {
    //字符串转list<String>
    List<String> list;
    if (lis.size() >= lis2.size()){
      list= lis.stream().filter(t-> !lis2.contains(t)).collect(Collectors.toList());
    }else {
      list= lis2.stream().filter(t-> !lis.contains(t)).collect(Collectors.toList());
    }
    return CollectionUtils.isEmpty(list);
  }
}
