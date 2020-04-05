package com.system.my.mapper.productMapper;

import com.system.my.base.product.CheckDTO;
import com.system.my.base.product.ProductSkuValueDTO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/4 10:39
 * @describe
 **/
public interface SkuKeyValueMapper {
    /**
     * 添加Sku value
     * @param productSkuValueDTO sku value bto
     */
    void addSkuValue(ProductSkuValueDTO productSkuValueDTO);

    /**
     * 根据key ID 查值
     * @param keyId keyid
     * @return value对象
     */
    List<ProductSkuValueDTO> selectByKeyId(Long keyId);

    /**
     * 查重
     * @param checkDTO sku name
     * @return sku VALUE
     */
    ProductSkuValueDTO checkExist(CheckDTO checkDTO);

    /**
     * 根据keyName得到id
     * @param name name
     * @return ID
     */
    Long selectIdByName(String name);

    /**
     * 通过值得id找到属性名
     * @param valueId 值的id
     * @return 属性名
     */
    ProductSkuValueDTO selectNameById(Long valueId);

    /**
     * 更新规格 的值
     * @param productSkuValueDTO 规格值
     */
    void updateSkuValue(ProductSkuValueDTO productSkuValueDTO);
}
