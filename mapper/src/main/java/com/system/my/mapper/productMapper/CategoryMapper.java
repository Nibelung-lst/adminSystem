package com.system.my.mapper.productMapper;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.CategoryParentVO;
import com.system.my.base.product.Page;
import com.system.my.base.product.ProductCategoryDTO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/3 9:46
 * @describe
 **/
public interface CategoryMapper {
    /**
     * 得到所有分类
     * @return 分类DTO
     */
    List<ProductCategoryDTO> getAllCategory();
    /**
     * 得到分类图片
     * @param categoryCode 分类编号
     * @return 图片地址
     */
    ProductCategoryDTO getCategoryByCategoryCode(String categoryCode);

    /**
     * 计数
     * @param parentCode 父节点编号
     * @return 计数
     */
    Integer countCategoryChildren(String parentCode);

    /**
     * 计数
     * @return
     */
    Integer countAllCategoryParent();

    /**
     * 给微信小程序得到次级分类
     * @param parentCode 父分类Code
     * @return list
     */
    List<ProductCategoryDTO> getByParentCodeWxAPI(String parentCode);

    /**
     *  查询最高父节点分类
     * @return 父节点分类List
     */
    List<ProductCategoryDTO> foreGetCategoryNoParent();
    /**
     *  查询最高父节点分类
     * @param page 分页
     * @return 父节点分类List
     */
    List<ProductCategoryDTO> getCategoryNoParent(Page page);

    /**
     * 得到子节点分类
     * @param page 父节点CODE
     * @return 分类list
     */
    List<ProductCategoryDTO> getByParentCode(Page page);

    /**
     * 分类编号查重
     * @param categoryCode 分类编号
     * @return boolean
     */
    ProductCategoryDTO categoryCodeCheck(String categoryCode);

    /**
     * 新增分类
     * @param productCategoryDTO 分类dto
     */
    void insert(ProductCategoryDTO productCategoryDTO);

    /**
     * 更新分类
     * @param productCategoryDTO 分类dto
     */
    void update(ProductCategoryDTO productCategoryDTO);

    /**
     * 得到所有的子节点分类
     * @return 子节点分类list
     */
    List<ProductCategoryDTO> getAllChildrenCategory();
}
