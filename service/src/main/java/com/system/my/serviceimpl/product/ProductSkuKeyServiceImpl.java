package com.system.my.serviceimpl.product;

import com.system.my.base.product.CheckDTO;
import com.system.my.base.product.ProductSkuKeyDTO;
import com.system.my.mapper.productMapper.SkuKeyMapper;
import com.system.my.service.procudt.ProductSkuKeyService;
import com.system.my.service.procudt.ProductSkuValueService;
import com.system.my.utils.exception.ProductException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 9:33 2019/10/28
 * @Description :SKU 键服务实现类
 */
@Service
public class ProductSkuKeyServiceImpl implements ProductSkuKeyService {
    /**
     * sku键DAO
     */
    @Resource
    SkuKeyMapper productSkuKeyDAO;
    /**
     * SKU值服务
     */
    @Resource
    ProductSkuValueService productSkuValueService;

    /**
     * 更新
     * @param productSkuKeyDTO 规格key
     */
    @Override
    public void updateSkuKey(ProductSkuKeyDTO productSkuKeyDTO) {
        productSkuKeyDAO.updateSkuKey(productSkuKeyDTO);
    }

    /**
     * 添加sku
     * @param productSkuKeyDTO sku key dto
     */
    @Override
    public void addSkuKey(ProductSkuKeyDTO productSkuKeyDTO) {
        if (!checkKeyName(productSkuKeyDTO.getName(),productSkuKeyDTO.getSpuCode())){
            throw new ProductException("该SPU下已存在相应的规格Key");
        }
        productSkuKeyDAO.addSkuKey(productSkuKeyDTO);
    }

    /**
     * 检查名
     * @param skuKeyName skuKeyName
     * @param spuCode 商品编号
     * @return boolean
     */
    @Override
    public Boolean checkKeyName(String skuKeyName,String spuCode) {
        CheckDTO checkDTO = new CheckDTO(skuKeyName,spuCode);
        return productSkuKeyDAO.checkExist(checkDTO) == null;
    }

    /**
     * 后台规格管理 显示key
     * @param spuCode 商品编号
     * @return key list
     */
    @Override
    public List<ProductSkuKeyDTO> queryKeyBySpuCode(String spuCode) {
        List<ProductSkuKeyDTO> productSkuKeyDTO =  productSkuKeyDAO.selectBySpuCode(spuCode);
        return productSkuKeyDTO;
    }

    /**
     * 根据键值Id查询值名
     * @param keyId 键值Id
     * @return 值名
     */
    @Override
    public String queryNameByKeyId(Long keyId) {
        ProductSkuKeyDTO productSkuKeyDTO = productSkuKeyDAO.selectKeyById(keyId);
        if (productSkuKeyDTO == null){
            throw new ProductException("找不到对应的值");
        }
        return productSkuKeyDTO.getName();
    }

}
