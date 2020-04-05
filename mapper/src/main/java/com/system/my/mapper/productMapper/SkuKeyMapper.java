package com.system.my.mapper.productMapper;

import com.system.my.base.product.CheckDTO;
import com.system.my.base.product.ProductSkuKeyDTO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/4 10:33
 * @describe
 **/
public interface SkuKeyMapper {
    /**
     * 添加sku key
     * @param productSkuKeyDTO sku key dto
     */
    void addSkuKey(ProductSkuKeyDTO productSkuKeyDTO);

    /**
     * 更新spu的key
     * @param productSkuKeyDTO spu key
     */
    void updateSkuKey(ProductSkuKeyDTO productSkuKeyDTO);

    /**
     * 查重
     * @param checkDTO sku name
     * @return sku key
     */
    ProductSkuKeyDTO checkExist(CheckDTO checkDTO);

    /**
     * 根据keyName得到id
     * @param name name
     * @return ID
     */
    Long selectIdByName(String name);

    /**
     * 根据商品编号得到key
     * @param spuCode 分类编号
     * @return key list
     */
    List<ProductSkuKeyDTO> selectBySpuCode(String spuCode);

    /**
     * 根据主键ID进行查询
     * @param keyId 主键ID
     * @return key对象
     */
    ProductSkuKeyDTO selectKeyById(Long keyId);
}
