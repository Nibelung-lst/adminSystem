package com.system.my.serviceimpl.product;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.Page;
import com.system.my.base.product.ProductDTO;
import com.system.my.base.product.ProductSkuDTO;
import com.system.my.base.product.ProductSkuKeyDTO;
import com.system.my.base.product.ProductSkuValueDTO;
import com.system.my.base.product.SkuCode;
import com.system.my.base.product.WeChatProductSkuDTO;
import com.system.my.mapper.productMapper.SkuMapper;
import com.system.my.service.procudt.ProductService;
import com.system.my.service.procudt.ProductSkuKeyService;
import com.system.my.service.procudt.ProductSkuService;
import com.system.my.service.procudt.ProductSkuValueService;
import com.system.my.utils.CrossUtils;
import com.system.my.utils.exception.ProductException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 9:11 2019/10/28
 * @Description :SKU服务实现类
 */
@Service
public class ProductSkuServiceImpl implements ProductSkuService {
    /**
     * skuDAO
     */
    @Resource
    SkuMapper productSkuDAO;
    /**
     * sku键服务
     */
    @Resource
    ProductSkuKeyService productSkuKeyService;
    /**
     * sku值服务
     */
    @Resource
    ProductSkuValueService productSkuValueService;
    /**
     * spu服务
     */
    @Resource
    ProductService productService;

    /**
     * 根据Spu编号得到sku编号LIST
     * @param spuCode Spu编号
     * @return SKU编号list
     */
    @Override
    public List<SkuCode> querySkuCodeBySpuCode(String spuCode) {
        return productSkuDAO.selectSkuCodeBySpuCode(spuCode);
    }

    /**
     * 一键创建sku
     * @param spuCode SPUCode
     */
    @Override
    public void createProductSku(String spuCode) throws Exception{
        Integer count = productSkuDAO.countAllSkuBySpuCode(spuCode)+1;
        List<ProductSkuKeyDTO> productSkuKeyDTOList = productSkuKeyService.queryKeyBySpuCode(spuCode);
        if (productSkuKeyDTOList == null){
            throw new ProductException("该spu还没有规格");
        }
        ProductSkuDTO oldProductSkuDTO = new ProductSkuDTO();
        oldProductSkuDTO.setSpuCode(spuCode);
        oldProductSkuDTO.setDefaultMarkup(true);
        productSkuDAO.updateSkuBySpuCode(oldProductSkuDTO);
        List<List<String>> result = new ArrayList<>();
        for (ProductSkuKeyDTO productSkuKeyDTO:productSkuKeyDTOList){
            List<String> specs = new ArrayList<>();
            List<ProductSkuValueDTO> productSkuValueDTOList = productSkuValueService.queryByAttributeKeyId(productSkuKeyDTO.getId());
                if (productSkuValueDTOList == null){
                    throw new ProductException("该规格没有对应的值");
                }
                for (ProductSkuValueDTO productSkuValueDTO:productSkuValueDTOList){
                    Long value = productSkuValueDTO.getId();
                    String base = String.valueOf(value);
//                    String base = String.format("%04d",value);
                    specs.add(base);
                }
                result.add(specs);
        }
        result = CrossUtils.getDescartes(result);
        int flag = 0;
        for (List<String> strings :result){
            String code = String.format("%04d",count);
            ProductSkuDTO productSkuDTO = new ProductSkuDTO();
            productSkuDTO.setUniqueCode(String.join(",",strings));
            productSkuDTO.setDescribe(productSkuValueService.querySpecsNameById(productSkuDTO.getUniqueCode()));
            productSkuDTO.setSpuCode(spuCode);
            productSkuDTO.setDefaultMarkup(false);
            productSkuDTO.setStatus(false);
            productSkuDTO.setSkuCode(spuCode+"-sk-"+code);
            productSkuDAO.addProductSku(productSkuDTO);
            if (flag == 0){
                ProductDTO productDTO = new ProductDTO(spuCode,spuCode+code);
                productService.updateBySpuCode(productDTO);
                flag = 1;
            }
            count++;
        }


    }


    /**
     * 后台根据spu显示sku
     * @param spuCode spu的编号
     * @return pageInfo
     */
    @Override
    public PageHelper<ProductSkuDTO> queryBySpuCode(String spuCode, Integer pageNumber, Integer pageSize) {
        Integer count = productSkuDAO.countBySpuCode(spuCode);
        Page page = new Page((pageNumber-1)*pageSize,pageSize,spuCode);
        List<ProductSkuDTO> productSkuDTO = productSkuDAO.selectBySpuCode(page);
        return new PageHelper<>(pageNumber,pageSize,count,productSkuDTO,5);
    }

    /**
     * 更新sku
     * @param productSkuDTO sku dto
     */
    @Override
    public void updateProductSku(ProductSkuDTO productSkuDTO) {
        productSkuDAO.update(productSkuDTO);
    }

    /**
     * 根据skuCode查询 sku
     * @param skuCode sku编号
     * @return skuDTO
     */
    @Override
    public ProductSkuDTO querySkuBySkuCode(String skuCode) {
        return productSkuDAO.selectSkuBySkuCode(skuCode);
    }

    /**
     *
     * @param spuCode
     * @param attributeId
     * @return
     * @throws Exception
     */
    @Override
    public ProductSkuDTO querySkuBySkuCodeAndId(String spuCode, List<String> attributeId) throws Exception {
        List<ProductSkuDTO> skuDTOList = productSkuDAO.selectSkuBySpuCode(spuCode);
        for (ProductSkuDTO productSkuDTO: skuDTOList){
            List<String> lis = Arrays.asList(productSkuDTO.getUniqueCode().split(","));
            if (CrossUtils.CompareStringByChar(lis,attributeId)){
                return productSkuDTO;
            }
        }
        return null;
    }

    /**
     *
     * @param spuCode
     * @return
     * @throws Exception
     */
    @Override
    public List<ProductSkuDTO> querySkuBySpuCode(String spuCode) throws Exception {
        return productSkuDAO.selectSkuBySpuCode(spuCode);
    }


}
