package com.system.my.serviceimpl.product;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.CategoryParentVO;
import com.system.my.base.product.Page;
import com.system.my.base.product.ProductCategoryDTO;
import com.system.my.mapper.productMapper.CategoryMapper;
import com.system.my.service.procudt.ProductCategoryService;
import com.system.my.utils.exception.ProductException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 16:04 2019/10/22
 * @Description : 分类服务实现类
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    /**
     * 分类DAO
     */
    @Resource
    CategoryMapper productCategoryDAO;

    @Override
    public String queryIconImageByCategoryCode(String categoryCode) throws Exception {
        ProductCategoryDTO productCategoryDTO = productCategoryDAO.getCategoryByCategoryCode(categoryCode);
        return productCategoryDTO.getIconImage();
    }

    @Override
    public List<ProductCategoryDTO> queryAllChildrenCategory() throws Exception {
        return productCategoryDAO.getAllChildrenCategory();
    }

    /**
     * 得到全部分类树
     * @return 分类树
     */
    @Override
    public List<ProductCategoryDTO> queryAllCategoryTree() {
        return productCategoryDAO.getAllCategory();
    }

    /**
     * 根据分类编号得到图片
     * @param categoryCode 分类编号
     * @return 图片地址
     */
    @Override
    public String queryCategoryImageByCategoryCode(String categoryCode) {
        String imageUrl = "http://10.104.131.160/";
        ProductCategoryDTO productCategoryDTO = productCategoryDAO.getCategoryByCategoryCode(categoryCode);
        return imageUrl+productCategoryDTO.getImage();
    }

    /**
     * 给小程序分类的接口
     * @return 最高分类list
     */
    @Override
    public List<CategoryParentVO> queryCategoryParent() {
        List<ProductCategoryDTO> list = productCategoryDAO.foreGetCategoryNoParent();
        if (list == null){
            throw new ProductException("没有任何分类信息");
        }
        List<CategoryParentVO> result = new ArrayList<>();
        for (ProductCategoryDTO productCategoryDTO : list){
            CategoryParentVO categoryParentVO = new CategoryParentVO();
            categoryParentVO.setCategoryCode(productCategoryDTO.getCategoryCode());
            categoryParentVO.setName(productCategoryDTO.getName());
            categoryParentVO.setStatus(productCategoryDTO.getStatus());
            result.add(categoryParentVO);
        }
        return result;
    }


    /**
     * 得到没有父节点的分类
     *
     * @return category LIST
     */
    @Override
    public PageHelper<ProductCategoryDTO> queryCategoryHighestParent(Integer pageNumber, Integer pageSize) {
        Integer count = productCategoryDAO.countAllCategoryParent();
        Page page = new Page((pageNumber-1)*pageSize,pageSize);
        List<ProductCategoryDTO> productCategory = productCategoryDAO.getCategoryNoParent(page);
        PageHelper<ProductCategoryDTO> pageHelper = new PageHelper<>(pageNumber,pageSize,count,productCategory,5);
        return pageHelper;
    }

    /**
     * 得到子节点
     *
     * @param parentCode   父节点id
     * @return pageInfo
     */
    @Override
    public PageHelper<ProductCategoryDTO> queryChildrenCategory(String parentCode,Integer pageNumber,Integer pageSize) {
            Integer count = productCategoryDAO.countCategoryChildren(parentCode);
            Page page = new Page((pageNumber-1)*pageSize,pageSize,parentCode);
            List<ProductCategoryDTO> productCategories = productCategoryDAO.getByParentCode(page);
            PageHelper<ProductCategoryDTO> pageHelper = new PageHelper<>(pageNumber,pageSize,count,productCategories,5);
        return pageHelper;
    }

    /**
     * 判断分类编号是否重复
     *
     * @param categoryCode 分类编号
     * @return boolean
     */
    @Override
    public Boolean checkExist(String categoryCode) {
        ProductCategoryDTO categoryName = productCategoryDAO.categoryCodeCheck(categoryCode);
        return categoryName == null;
    }

    @Override
    public ProductCategoryDTO queryCategoryByCategoryCode(String categoryCode) {
        return productCategoryDAO.categoryCodeCheck(categoryCode);
    }

    /**
     * 添加分类
     *
     * @param productCategoryDTO 分类dto
     */
    @Override
    public void addCategory(ProductCategoryDTO productCategoryDTO) {
        if (!checkExist(productCategoryDTO.getCategoryCode())){
            throw new ProductException("分类编号重复");
        }
        productCategoryDAO.insert(productCategoryDTO);
    }

    /**
     * 更新分类
     *
     * @param productCategoryDTO 商品分类dto
     */
    @Override
    public void updateCategory(ProductCategoryDTO productCategoryDTO) {
        if (checkExist(productCategoryDTO.getCategoryCode())){
            throw new ProductException("找不到分类");
        }
        productCategoryDAO.update(productCategoryDTO);
    }

    /**
     * 给小程序得到子节点分类
     * @param parentCode 父编号
     * @return 分类list
     */
    @Override
    public List<ProductCategoryDTO> queryChildrenCategoryWxApi(String parentCode) {
        List<ProductCategoryDTO> productCategoryDTOS = productCategoryDAO.getByParentCodeWxAPI(parentCode);
        for (ProductCategoryDTO productCategoryDTO : productCategoryDTOS){
            String imageUrl = "http://10.104.131.160/";
            String resultImage = productCategoryDTO.getImage();
            productCategoryDTO.setImage(imageUrl+resultImage);
        }
        return productCategoryDTOS;
    }
}


