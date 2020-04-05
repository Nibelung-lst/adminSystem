package com.system.my.service.procudt;


import com.system.my.base.product.ProductSkuKeyDTO;

import java.util.List;

/**
 * @Author    :Nibelung
 * @Date      ：Created in 9:18 2019/10/28
 * @Description : sku key service
 */
public interface ProductSkuKeyService {

    /**
     * 更新Sku key
     * @param productSkuKeyDTO skuKEY dto
     * @throws Exception 业务异常
     */
    void updateSkuKey(ProductSkuKeyDTO productSkuKeyDTO) throws Exception;
    /**
     * 添加sku key
     * @param productSkuKeyDTO sku key dto
     * @throws Exception 业务异常
     */
    void addSkuKey(ProductSkuKeyDTO productSkuKeyDTO) throws Exception;

    /**
     * skuKey 名字查重
     * @param skuKeyName skuKeyName
     * @param spuCode 商品编号
     * @return boolean
     * @throws Exception 业务异常
     */
    Boolean checkKeyName(String skuKeyName, String spuCode) throws Exception;

    /**
     * 后台规格管理 显示key
     * @param spuCode 商品编号
     * @return key list
     * @throws Exception 业务异常
     */
    List<ProductSkuKeyDTO> queryKeyBySpuCode(String spuCode) throws Exception;

    /**
     * 根据键值Id查询值名
     * @param keyId 键值Id
     * @return 值名
     * @throws Exception 业务异常
     */
    String queryNameByKeyId(Long keyId) throws Exception;
}
