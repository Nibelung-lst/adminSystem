package com.system.my.service.procudt;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.ProductSkuDTO;
import com.system.my.base.product.SkuCode;
import com.system.my.base.product.WeChatProductSkuDTO;

import java.util.List;

/**
 * @Author    :Nibelung
 * @Date      ：Created in 9:10 2019/10/28
 * @Description : sku service
 */
public interface ProductSkuService {
    /**
     * 根据SpuCode 得到SkuCode
     * @param spuCode Spu编号
     * @return skuCode list
     * @throws Exception 业务异常
     */
    List<SkuCode> querySkuCodeBySpuCode(String spuCode) throws Exception;
    /**
     * 一键生成SKU
     * @param spuCode SPUCode
     * @throws Exception 业务异常
     */
    void createProductSku(String spuCode) throws Exception;


    /**
     * 后台根据spu显示SKU
     * @param spuCode spu编号
     * @param pageNumber 第几页
     * @param pageSize  每页显示几条
     * @return pageHelper SKU
     * @throws Exception 业务异常
     */
    PageHelper<ProductSkuDTO> queryBySpuCode(String spuCode, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 更新sku
     * @param productSkuDTO sku dto
     * @throws Exception 业务异常
     */
    void updateProductSku(ProductSkuDTO productSkuDTO) throws Exception;

    /**
     * 根据skuCode获得sku
     * @param skuCode sku编号
     * @return sku dto
     * @throws Exception 业务异常
     */
    ProductSkuDTO querySkuBySkuCode(String skuCode) throws Exception;


    /**
     * 根据skuCode获得sku
     * @param attributeId
     * @return
     * @throws Exception
     */
    ProductSkuDTO querySkuBySkuCodeAndId(String spuCode,List<String> attributeId) throws Exception;

    /**
     *
     * @param spuCode
     * @return
     * @throws Exception
     */
    List<ProductSkuDTO> querySkuBySpuCode(String spuCode)throws Exception;
}
