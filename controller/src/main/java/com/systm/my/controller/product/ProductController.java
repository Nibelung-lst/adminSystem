package com.systm.my.controller.product;

import com.system.my.base.common.PageHelper;
import com.system.my.base.product.ProductDTO;
import com.system.my.base.product.ProductSkuDTO;
import com.system.my.base.product.ProductSkuKeyDTO;
import com.system.my.base.utils.Result;
import com.system.my.base.utils.ResultUtil;
import com.system.my.service.procudt.ProductService;
import com.system.my.service.procudt.ProductSkuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author :Nibelung
 * @Date ：Created in 15:37 2019/10/28
 * @Description :
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Resource
    ProductSkuService productSkuRemoteService;
    @Resource
    ProductService productRemoteService;


    @CrossOrigin
    @GetMapping(value = "getAttribute")
    @ResponseBody
    public Result getAttribute(@RequestParam(value = "spuCode") @NotBlank String spuCode) throws Exception {
        List<ProductSkuKeyDTO> attributeList =  productRemoteService.getAttributeBySpuCode(spuCode);
        return ResultUtil.success(attributeList);
    }
    /**
     * 一键生成sku
     * @param spuCode spu的编号
     * @return result
     */
    @CrossOrigin
    @GetMapping(value = "createSku")
    @ResponseBody
    public Result createSku(@RequestParam(value = "spuCode") @NotBlank String spuCode) throws Exception {
        productSkuRemoteService.createProductSku(spuCode);
        return ResultUtil.success("添加成功",true);
    }

    @CrossOrigin
    @GetMapping(value = "getSku")
    @ResponseBody
    public Result getSku (@RequestParam(value = "spuCode")@NotBlank String spuCode,
                          @RequestParam(value = "id")@NotBlank List<String> attributeId) throws Exception {
        ProductSkuDTO sku = productSkuRemoteService.querySkuBySkuCodeAndId(spuCode,attributeId);
        return ResultUtil.success(sku);
    }
    /**
     * 检查spu编号
     * @param spuCode spu编号
     * @return result
     */
    @CrossOrigin
    @GetMapping(value = "checkSpuCode")
    @ResponseBody
    public Result checkSpuCode(@RequestParam(value = "spuCode")@NotBlank String spuCode) throws Exception {
        boolean isStatus = productRemoteService.checkExist(spuCode);
        if (isStatus){
            return ResultUtil.error(1,"spu编号可用");
        }
        else {
            return ResultUtil.error(0,"spu编号重复");
        }
    }
    /**
     * 添加spu
     * @param productSpuAO spu ao
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "addProductSpu")
    @ResponseBody
    public Result addProductSpu(@Valid @RequestBody ProductDTO productSpuAO) throws Exception {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setBrandName(productSpuAO.getBrandName());
            productDTO.setStatus(productSpuAO.getStatus());
            productDTO.setSpuName(productSpuAO.getSpuName());
            //-------------------------------修改的地方-----------------------
            productDTO.setDefaultSkuCode(productSpuAO.getDefaultSkuCode());
            //--------------------------------------------------------
            productDTO.setSpuDescribe(productSpuAO.getSpuDescribe());

            productRemoteService.addProductSpu(productDTO);
            return ResultUtil.success("添加成功",true);
    }

    /**
     * 更新spu状态
     * @param productSpuAO spuAO
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "updateSpuStatue")
    @ResponseBody
    public Result updateSpuStatue(@Valid @RequestBody ProductDTO productSpuAO) throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSpuCode(productSpuAO.getSpuCode());
        productDTO.setStatus(productSpuAO.getStatus());
        productRemoteService.updateBySpuCode(productDTO);
        return ResultUtil.success("更新成功",true);
    }

    /**
     * 修改spu
     * @param productSpuAO spuAO
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "updateSpu")
    @ResponseBody
    public Result updateSpu(@Valid @RequestBody ProductDTO productSpuAO) throws Exception {
        productRemoteService.updateBySpuCode(productSpuAO);
        return ResultUtil.success("修改成功",true);
    }

    /**分页
     * 根据分类编号得到spu
     * @param categoryCode 分类编号
     * @return result
     */
    @CrossOrigin
    @GetMapping(value = "getSpuByCategoryCode")
    @ResponseBody
    public Result getSpuByCategoryCode(@RequestParam(value = "categoryCode")@NotBlank String categoryCode,
                                       @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber,
                                       @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize) throws Exception {
        PageHelper<ProductDTO> pageInfo = productRemoteService.querySpuByCategoryCode(categoryCode, pageNumber, pageSize);
        return ResultUtil.success(pageInfo);
    }

    /**分页
     * 根据分类编号得到spu
     * @return result
     */
    @CrossOrigin
    @GetMapping(value = "getSpu")
    @ResponseBody
    public Result getSpuByCategoryCode(@RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber,
                                       @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize) throws Exception {
        PageHelper<ProductDTO> pageInfo = productRemoteService.querySpu(pageNumber, pageSize);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 修改sku状态
     * @param
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "updateSkuStatue")
    @ResponseBody
    public Result updateSkuStatue(@Valid @RequestBody ProductSkuDTO productSkuDTO) throws Exception {
        productSkuRemoteService.updateProductSku(productSkuDTO);
        return ResultUtil.success("修改成功",true);
    }

    /**
     * 修改sku
     * @param
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "updateSku")
    @ResponseBody
    public Result updateSku(@Valid @RequestBody ProductSkuDTO productSkuDTO) throws Exception {
        //--------------------------管理员接口--------------------------
        productSkuRemoteService.updateProductSku(productSkuDTO);
        return ResultUtil.success("修改成功",true);
    }

    /**
     * 根据关键字得到商品信息
     * @param keyWord 关键字
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "getProductByKeyWord")
    @ResponseBody
    public Result getProductByKeyWord(@RequestParam(value = "keyWord") @NotBlank String keyWord) throws Exception {
        return ResultUtil.success(productRemoteService.querySpuByKeyWord(keyWord));
    }
    /**
     * 根据spu编号 得到sku 及其分类
     * @param spuCode spu编号
     * @return sku page
     */
    @CrossOrigin
    @GetMapping(value = "getSkuBySpuCode")
    @ResponseBody
    public Result getSkuBySpuCode(@RequestParam(value = "spuCode")@NotBlank String spuCode) throws Exception {
        List<ProductSkuDTO> pageInfo = productSkuRemoteService.querySkuBySpuCode(spuCode);
        return ResultUtil.success(pageInfo);
    }

}
