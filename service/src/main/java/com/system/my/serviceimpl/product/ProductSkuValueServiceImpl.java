package com.system.my.serviceimpl.product;

import com.system.my.base.product.CheckDTO;
import com.system.my.base.product.ProductSkuValueDTO;
import com.system.my.mapper.productMapper.SkuKeyValueMapper;
import com.system.my.service.procudt.ProductSkuKeyService;
import com.system.my.service.procudt.ProductSkuValueService;
import com.system.my.utils.StringUtils;
import com.system.my.utils.exception.ProductException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 9:53 2019/10/28
 * @Description :SKU 规格值服务实现类
 */
@Service
public class ProductSkuValueServiceImpl implements ProductSkuValueService {
    /**
     * SKU规格值DAO
     */
    @Resource
    SkuKeyValueMapper productSkuValueDAO;
    /**
     * SKU KEY 服务
     */
    @Resource
    ProductSkuKeyService productSkuKeyService;

    /**
     * 更新
     * @param productSkuValueDTO 规格value
     */
    @Override
    public void updateSkuValue(ProductSkuValueDTO productSkuValueDTO) {
        productSkuValueDAO.updateSkuValue(productSkuValueDTO);
    }

    /**
     * 添加sku的值
     * @param productSkuValueDTO sku value dto
     */
    @Override
    public void addSkuValue(ProductSkuValueDTO productSkuValueDTO) {
        if (!checkValue(productSkuValueDTO.getName(), productSkuValueDTO.getAttributeKeyId())){
            throw new ProductException("规格值重复");
        }
        productSkuValueDAO.addSkuValue(productSkuValueDTO);
    }

    /**
     *根绝键的ID找到 值
     * @param keyId 值的前值
     * @return 值LIST
     */
    @Override
    public List<ProductSkuValueDTO> queryByAttributeKeyId(Long keyId) {
        return productSkuValueDAO.selectByKeyId(keyId);
    }

    /**
     * 查询Id by name
     * @param skuKeyName sku key name
     * @return id
     */
    @Override
    public Long queryValueByName(String skuKeyName) {
        return productSkuValueDAO.selectIdByName(skuKeyName);
    }

    @Override
    public boolean checkValue(String skuValueName,Long condition) {
        CheckDTO checkDTO = new CheckDTO(skuValueName,condition);
            return productSkuValueDAO.checkExist(checkDTO) == null;
    }

    /**
     * 根据唯一编码得到属性值
     * @param uniqueCode 唯一编号
     * @return 属性值字符串
     */
    @Override
    public String querySpecsNameById(String uniqueCode) throws Exception {
        String[] uniqueCodeSplit = uniqueCode.split(",");
        StringBuilder valueSplit = new StringBuilder();
        for (String split : uniqueCodeSplit){
            ProductSkuValueDTO productSkuValueDTO = productSkuValueDAO.selectNameById(Long.valueOf(split));
            if (productSkuValueDTO == null){
                throw new ProductException("没有找到相应的规格");
            }
            String keyName = productSkuKeyService.queryNameByKeyId(productSkuValueDTO.getAttributeKeyId());
            String valueName = productSkuValueDTO.getName();
            valueSplit.append(keyName);
            valueSplit.append(":");
            valueSplit.append(valueName);
            valueSplit.append(" ");
        }
        return valueSplit.toString();
    }
}
