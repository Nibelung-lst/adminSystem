package com.system.my.mapper.productMapper;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.Page;
import com.system.my.base.product.ProductSkuDTO;
import com.system.my.base.product.SkuCode;
import com.system.my.base.product.WeChatProductSkuDTO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/3 13:53
 * @describe
 **/
public interface SkuMapper {

    /**
     * 根据spu编号得到skuCode
     * @param spuCode spu编号
     * @return skuCode lsit
     */
    List<SkuCode> selectSkuCodeBySpuCode(String spuCode);
    /**
     * 根据skuCode查询图片
     * @param skuCode sku编号
     * @return 图片路径
     */
    String selectSkuImageBySkuCode(String skuCode);
    /**
     * spu计数
     * @param spuCode 分类编号
     * @return 计数
     */
    Integer countAllSkuBySpuCode(String spuCode);
    /**
     * spu计数
     * @param spuCode 分类编号
     * @return 计数
     */
    Integer countBySpuCode(String spuCode);
    /**
     *根据skuCode更新Sku
     * @param productSkuDTO
     */
    void updateSkuBySpuCode(ProductSkuDTO productSkuDTO);
    /**
     * 根据sku编号查询sku
     * @param skuCode sku编号
     * @return skuDTO
     */
    ProductSkuDTO selectSkuBySkuCode(String skuCode);
    /**
     * 添加sku
     * @param productSkuDTO sku dto
     */
    void addProductSku(ProductSkuDTO productSkuDTO);

    /**
     * 根据唯一编号找到相应的sku
     * @param uniqueCode 唯一编号
     * @return Sku ao
     */
    ProductSkuDTO selectSkuByUniqueCode(String uniqueCode);

    /**
     * 根据SpuCode得到所有相应的sku
     * @param page 分页
     * @return sku list
     */
    List<ProductSkuDTO> selectBySpuCode(Page page);

    /**
     *
     * @param spuCode
     * @return
     */
    List<ProductSkuDTO> selectSkuBySpuCode(String spuCode);

    /**
     * 更新sku
     * @param productSkuDTO sku dto
     */
    void update(ProductSkuDTO productSkuDTO);
}
