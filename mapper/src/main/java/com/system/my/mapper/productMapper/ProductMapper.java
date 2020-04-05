package com.system.my.mapper.productMapper;

import com.system.my.base.product.Page;
import com.system.my.base.product.ProductDTO;

import java.util.List;

/**
 * @author shitao.liu@luckincoffee.com
 * @date 2020/4/3 9:24
 * @describe
 **/
public interface ProductMapper {
    /**
     * 在售商品的数量
     * @return 数量
     */
    Integer countSpu();
    /**
     * 随机取出几条商品数据
     * @param randomNumber 分页条件
     * @return productList
     */
    List<ProductDTO> selectSpuByRandom(Integer randomNumber);
    /**
     * 关键字查询
     * @param keyWord 关键字
     * @return 分类DTO
     */
    List<ProductDTO> selectSpuByKeyWord(String keyWord);
    /**
     * spu计数
     * @param categoryCode 分类编号
     * @return 计数
     */
    Integer countByCategoryCode(String categoryCode);
    /**
     * 根据spuCode 查询
     * @param spuCode spuCode
     * @return SPU DTO
     */
    ProductDTO selectSpuBySpuCode(String spuCode);
    /**
     * 添加spu
     * @param productDTO spu dto
     */
    void addProductSpu(ProductDTO productDTO);

    /**
     * spu编号查重
     * @param spuCode spu编号
     * @return spu对象
     */
    ProductDTO spuCodeCheck(String spuCode);

    /**
     * 根据spu编号更新spu
     * @param productDTO spu对象
     */
    void updateBySpuCode(ProductDTO productDTO);

    /**
     * 根据分类编号查询SPU
     * @param page 分类编号
     * @return SPU list
     */
    List<ProductDTO> selectByCategoryCode(Page page);

    /**
     *
     * @param page
     * @return
     */
    List<ProductDTO> selectSpu(Page page);

    /**
     * 搜索商品分页
     * @param page 分页
     * @return 商品页
     */
    List<ProductDTO> selectSpuByKeyWordPage(Page page);

    /**
     * 搜索商品计数
     * @param keyWord 关键字
     * @return 搜索出来的商品数量
     */
    Integer countByKeyWord(String keyWord);
}
