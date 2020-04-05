package com.system.my.service.procudt;


import com.system.my.base.common.PageHelper;
import com.system.my.base.product.CategoryParentVO;
import com.system.my.base.product.ProductCategoryDTO;

import java.util.List;

/**
 * @Author    :Nibelung
 * @Date      ：Created in 16:06 2019/10/22
 * @Description :分类服务
 */
public interface ProductCategoryService {

    /**
     * 根据分类编号得到icon路径
     * @param categoryCode 分类编号
     * @return 图片路径
     * @throws Exception 业务错误
     */
    String queryIconImageByCategoryCode(String categoryCode)throws Exception;
    /**
     * 得到所有的子节点分类
     * @return 子节点分类list
     * @throws Exception 业务错误
     */
    List<ProductCategoryDTO> queryAllChildrenCategory()throws Exception;
    /**
     * 得到所有分类
     * @return 全部分类LIST
     * @throws Exception 业务异常
     */
    List<ProductCategoryDTO> queryAllCategoryTree() throws Exception;
    /**
     * 根据分类编号获得分类图片
     * @param categoryCode 分类编号
     * @return 图片地址
     * @throws Exception 业务异常
     */
    String queryCategoryImageByCategoryCode(String categoryCode) throws Exception;
    /**
     * 给小程序接口
     * @return 最高分类vo
     * @throws Exception 业务异常
     */
    List<CategoryParentVO> queryCategoryParent() throws Exception;

    /**
     * 得到最高父节点
     * @param pageNumber 页数
     * @param pageSize 每页显示几条
     * @return 最高父节点List
     * @throws Exception 业务异常
     */
    PageHelper<ProductCategoryDTO> queryCategoryHighestParent(Integer pageNumber, Integer pageSize) throws Exception;


    /**
     * 得到子节点
     * @param parentCode 父节点编号
     * @param pageNumber 页数
     * @param pageSize 页码
     * @return pageHelp
     * @throws Exception 业务异常
     */
    PageHelper<ProductCategoryDTO> queryChildrenCategory(String parentCode, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 分类编号查重
     * @param categoryCode 分类编号
     * @return boolean
     * @throws Exception 业务异常
     */
    Boolean checkExist(String categoryCode) throws Exception;

    /**
     * 根据分类编号查询分类
     * @param categoryCode 分类编号
     * @return 分类dto
     * @throws Exception 业务异常
     */
    ProductCategoryDTO queryCategoryByCategoryCode(String categoryCode) throws Exception;

    /**
     * 新增分类
     * @param productCategoryDTO 分类dto
     * @throws Exception 业务异常
     */
    void addCategory(ProductCategoryDTO productCategoryDTO) throws Exception;

    /**
     * 更新分类
     * @param productCategoryDTO 商品分类dto
     * @throws Exception 业务异常
     */
    void updateCategory(ProductCategoryDTO productCategoryDTO) throws Exception;

    /**
     * 得到子分类
     * @param parentCode 父编号
     * @return list
     * @throws Exception 业务异常
     */
    List<ProductCategoryDTO> queryChildrenCategoryWxApi(String parentCode) throws Exception;

}
