package com.system.my.service.procudt;


import com.system.my.base.product.ProductSkuValueDTO;

import java.util.List;

/**
 * @Author    :Nibelung
 * @Date      ：Created in 9:52 2019/10/28
 * @Description :Sku value service
 */
public interface ProductSkuValueService {

    /**
     * 更新value的值
     * @param productSkuValueDTO 规格value
     * @throws Exception 业务异常
     */
    void updateSkuValue(ProductSkuValueDTO productSkuValueDTO) throws Exception;
    /**
     * 添加sku的值
     * @param productSkuValueDTO sku 值
     * @throws Exception 业务异常
     */
    void addSkuValue(ProductSkuValueDTO productSkuValueDTO) throws Exception;

    /**
     * 根据keyId查询
     * @param keyId 值的前值
     * @return value对象
     * @throws Exception 业务异常
     */
    List<ProductSkuValueDTO> queryByAttributeKeyId(Long keyId) throws Exception;

    /**
     * 通过名字查找Id
     * @param skuKeyName sku key name
     * @return id
     * @throws Exception 业务异常
     */
    Long queryValueByName(String skuKeyName) throws Exception;

    /**
     * 值查重
     * @param skuValueName 值
     * @param condition 条件
     * @return boolean
     * @throws Exception 业务异常
     */
    boolean checkValue(String skuValueName, Long condition) throws Exception;

    /**
     * 通过唯一编号找到相应的值
     * @param uniqueCode 唯一编号
     * @return 值名
     * @throws Exception 业务异常
     */
    String querySpecsNameById(String uniqueCode) throws Exception;
}
