package com.system.my.serviceimpl.product;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.AttributeDTO;
import com.system.my.base.product.Page;
import com.system.my.base.product.ProductDTO;
import com.system.my.base.product.ProductDetailDTO;
import com.system.my.base.product.ProductSkuDTO;
import com.system.my.base.product.ProductSkuKeyDTO;
import com.system.my.base.product.ProductSkuValueDTO;
import com.system.my.base.product.WeChatProductSpuDTO;
import com.system.my.mapper.productMapper.ProductMapper;
import com.system.my.service.procudt.ProductCategoryService;
import com.system.my.service.procudt.ProductService;
import com.system.my.service.procudt.ProductSkuKeyService;
import com.system.my.service.procudt.ProductSkuService;
import com.system.my.service.procudt.ProductSkuValueService;
import com.system.my.utils.exception.ProductException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 15:37 2019/10/21
 * @Description : SPU 服务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    /**
     * SPU dao
     */
    @Resource
    ProductMapper productDAO;
    /**
     * SKU 服务
     */
    @Resource
    ProductSkuService productSkuService;
    /**
     * SKU 规格值服务
     */
    @Resource
    ProductSkuValueService productSkuValueService;
    /**
     * 分类服务
     */
    @Resource
    ProductCategoryService productCategoryService;
    /**
     * SKU 规格键服务
     */
    @Resource
    ProductSkuKeyService productSkuKeyService;

    /**
     *
     * @param spuCode
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductSkuKeyDTO> getAttributeBySpuCode(String spuCode) throws Exception {
        List<ProductSkuKeyDTO> keyList = productSkuKeyService.queryKeyBySpuCode(spuCode);
        for (ProductSkuKeyDTO key : keyList){
            List<ProductSkuValueDTO> valueList = productSkuValueService.queryByAttributeKeyId(key.getId());
            key.setProductSkuValueDTO(valueList);
        }
     return keyList;
    }

    /**
     * 关键字搜索
     * @param keyWord 关键字
     * @return 分类dto list
     */
    @Override
    public List<ProductDTO> querySpuByKeyWord(String keyWord) {
        return productDAO.selectSpuByKeyWord(keyWord);
    }

    /**
     * 添加spu
     * @param productDTO spu dto
     * @throws Exception 业务错误
     */
    @Override
    public void addProductSpu(ProductDTO productDTO) throws Exception{
        int count = productDAO.countSpu()+1;
        productDTO.setSpuCode("SP"+ count);
        productDAO.addProductSpu(productDTO);
    }

    /**
     * spu编号查重
     * @param spuCode spu编号
     * @return boolean
     */
    @Override
    public Boolean checkExist(String spuCode) {
        ProductDTO brandName = productDAO.spuCodeCheck(spuCode);
        return brandName == null;
    }

    /**
     * 修改商品
     * @param productDTO 商品spu对象
     */
    @Override
    public void updateBySpuCode(ProductDTO productDTO) {
        if (checkExist(productDTO.getSpuCode())){
            throw new ProductException("找不到相应的商品");
        }
        productDAO.updateBySpuCode(productDTO);
    }

    /**
     * 根据分类编号查找spu
     * @param categoryCode 分类编号
     * @return 商品SPU分页
     */
    @Override
    public PageHelper<ProductDTO> querySpuByCategoryCode(String categoryCode, Integer pageNumber, Integer pageSize) {
        Integer count = productDAO.countByCategoryCode(categoryCode);
        Page page = new Page((pageNumber-1)*pageSize,pageSize,categoryCode);
        List<ProductDTO> productDTO = productDAO.selectByCategoryCode(page);
        PageHelper<ProductDTO> pageHelper = new PageHelper<>(pageNumber,pageSize,count,productDTO,5);
        return pageHelper;
    }

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public PageHelper<ProductDTO> querySpu(Integer pageNumber, Integer pageSize) throws Exception {
        Integer count = productDAO.countSpu();
        Page page = new Page((pageNumber-1)*pageSize,pageSize);
        List<ProductDTO> productDTO = productDAO.selectSpu(page);
        return new PageHelper<>(pageNumber,pageSize,count,productDTO,5);
    }

    /**
     * 根据SpuCodeList得到相应的商品信息
     * @param spuCodes 商品编号List
     * @return 商品详情信息
     * @throws Exception 业务错误
     */
    @Override
    public List<ProductDetailDTO> queryProductBySpuCodeList(List<String> spuCodes) throws Exception {
        List<ProductDetailDTO> productDetailList = new ArrayList<>();
        for (String spuCode : spuCodes){
            if (spuCode.isEmpty()){
                throw new ProductException("spuCode为空");
            }
           ProductDetailDTO productDetailDTO = new ProductDetailDTO();
           ProductDTO productDTO = productDAO.selectSpuBySpuCode(spuCode);
           if (productDTO == null){
               throw new ProductException("根据spuCode没有找到相应的SPU");
           }
           if (productDTO.getDefaultSkuCode() == null){
               throw new ProductException("没有设置默认的sku");
           }
           ProductSkuDTO productSkuDTO = productSkuService.querySkuBySkuCode(productDTO.getDefaultSkuCode());
           if (productSkuDTO.getImage() == null){
               throw new ProductException("没有为sku设置图片");
           }
           productDetailDTO.setSpuCode(productDTO.getSpuCode());
           productDetailDTO.setSpuName(productDTO.getSpuName());
           productDetailDTO.setSpuImage(productSkuDTO.getImage());
           productDetailDTO.setCurrentPrice(productSkuDTO.getCurrentPrice());
           productDetailDTO.setOriginPrice(productSkuDTO.getOriginPrice());
           productDetailDTO.setSpuDescribe(productDTO.getSpuDescribe());
           productDetailDTO.setSpuStatus(productDTO.getStatus());
           productDetailList.add(productDetailDTO);
        }
        return productDetailList;
    }

    /**
     * 填充SpuDTO
     * @param productDTOList 商品DTO LIST
     * @return WeChatProductSpuDTO list
     * @throws Exception 业务错误
     */
    private List<WeChatProductSpuDTO> fillWxProductSpuDTO(List<ProductDTO> productDTOList) throws Exception {
        List<WeChatProductSpuDTO> productSpuDTOList = new ArrayList<>();
        for (ProductDTO result : productDTOList){
            WeChatProductSpuDTO wxProductSpuDTO = new WeChatProductSpuDTO();
            if (result.getDefaultSkuCode() == null){
                throw new ProductException("没有设置默认的sku");
            }
            ProductSkuDTO productSkuDTO = productSkuService.querySkuBySkuCode(result.getDefaultSkuCode());
            if (productSkuDTO.getImage() == null){
                throw new ProductException("没有为sku添加图片");
            }
            wxProductSpuDTO.setBrandCode(result.getBrandCode());
            wxProductSpuDTO.setBrandName(result.getBrandName());
            wxProductSpuDTO.setCategoryCode(result.getCategoryCode());
            wxProductSpuDTO.setSpuCode(result.getSpuCode());
            wxProductSpuDTO.setSpuName(result.getSpuName());
            wxProductSpuDTO.setSpuDescribe(result.getSpuDescribe());
            wxProductSpuDTO.setDefaultImage(productSkuDTO.getImage());
            wxProductSpuDTO.setDefaultCurrentPrice(productSkuDTO.getCurrentPrice());
            wxProductSpuDTO.setDefaultOriginPrice(productSkuDTO.getOriginPrice());
            productSpuDTOList.add(wxProductSpuDTO);
        }
        return productSpuDTOList;
    }
}
