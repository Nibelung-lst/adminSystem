package com.system.my.service.procudt;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.ProductDTO;
import com.system.my.base.product.ProductDetailDTO;
import com.system.my.base.product.ProductSkuKeyDTO;

import java.util.List;

/**
 * @Author    :Nibelung
 * @Date      ：Created in 15:37 2019/10/21
 * @Description :spu service
 */
public interface ProductService {
    /**
     * 关键字搜索
     * @param keyWord 关键字
     * @return 分类dto
     * @throws Exception 业务异常
     */
    List<ProductDTO> querySpuByKeyWord(String keyWord) throws Exception;
    /**
     * 添加spu
     * @param productDTO spu dto
     * @throws Exception 业务异常
     */
    void addProductSpu(ProductDTO productDTO) throws Exception;

    /**
     * spu编号查重
     * @param spuCode spu编号
     * @return boolean
     * @throws Exception 业务异常
     */
    Boolean checkExist(String spuCode) throws Exception;

    /**
     * 根据spuCode修改
     * @param productDTO 商品spu对象
     * @throws Exception 业务异常
     */
    void updateBySpuCode(ProductDTO productDTO) throws Exception;


    /**
     * 根据分类编号得到spu
     * @param categoryCode 分类编号
     * @param pageNumber 页数
     * @param pageSize 每页显示几条
     * @return pageHelper
     * @throws Exception 业务异常
     */
    PageHelper<ProductDTO> querySpuByCategoryCode(String categoryCode, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageHelper<ProductDTO> querySpu(Integer pageNumber, Integer pageSize) throws Exception;
    /**
     * 根据SpuCode得到相应的商品信息
     * @param spuCode 商品编号
     * @return 商品详情信息
     * @throws Exception 业务错误
     */
    List<ProductDetailDTO> queryProductBySpuCodeList(List<String> spuCode)throws Exception;

    /**
     *
     * @param spuCode
     * @return
     * @throws Exception
     */
    List<ProductSkuKeyDTO> getAttributeBySpuCode(String spuCode) throws Exception;
}
