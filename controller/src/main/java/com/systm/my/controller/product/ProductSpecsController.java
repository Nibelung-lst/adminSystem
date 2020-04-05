package com.systm.my.controller.product;

import com.system.my.base.product.ProductSkuKeyDTO;
import com.system.my.base.product.ProductSkuValueDTO;
import com.system.my.base.utils.Result;
import com.system.my.base.utils.ResultUtil;
import com.system.my.service.procudt.ProductSkuKeyService;
import com.system.my.service.procudt.ProductSkuService;
import com.system.my.service.procudt.ProductSkuValueService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author :Nibelung
 * @Date ：Created in 10:01 2019/10/28
 * @Description :Spu code
 */
@RestController
public class ProductSpecsController {
    @Resource
    ProductSkuService productSkuRemoteService;
    @Resource
    ProductSkuKeyService productSkuKeyService;
    @Resource
    ProductSkuValueService productSkuValueService;

    /**
     * 添加key
     * @param
     * @return result
     */
    @CrossOrigin
    @PostMapping(value = "api/addSpecsKey")
    @ResponseBody
    public Result addSpecsKey(@Valid @RequestBody ProductSkuKeyDTO productSkuKeyDTO) throws Exception {
             productSkuKeyService.addSkuKey(productSkuKeyDTO);
        return ResultUtil.success("添加成功",true);
    }
    @CrossOrigin
    @PostMapping(value = "api/addSpecsValue")
    @ResponseBody
    public Result addSpecsValue(@Valid @RequestBody ProductSkuValueDTO productSkuValueDTO) throws Exception {
        productSkuValueService.addSkuValue(productSkuValueDTO);
        return ResultUtil.success("添加成功",true);
    }

    /**
     * 后台得到value
     * @param keyId 属性值id
     * @return result
     */
    @CrossOrigin
    @GetMapping(value = "api/getSpecsValue")
    @ResponseBody
    public Result getSpecsValue(@RequestParam(value = "keyId") @NotBlank Long keyId) throws Exception {
        return ResultUtil.success(productSkuValueService.queryByAttributeKeyId(keyId));
    }
    /**
     * 后台得到key
     * @param spuCode spu编号
     * @return result
     */
    @CrossOrigin
    @GetMapping(value = "api/getSpecsKey")
    @ResponseBody
    public Result getSpecsKey(@RequestParam(value = "spuCode")@NotBlank String spuCode) throws Exception {
        return ResultUtil.success(productSkuKeyService.queryKeyBySpuCode(spuCode));
    }

    /**
     * 修改key
     * @param
     * @return 成功
     * @throws Exception 业务错误
     */
    @CrossOrigin
    @PostMapping(value = "api/updateSpecsKey")
    @ResponseBody
    public Result updateSpecsKey(@Valid @RequestBody ProductSkuKeyDTO productSkuKeyDTO) throws Exception {
        productSkuKeyService.updateSkuKey(productSkuKeyDTO);
        return ResultUtil.error(1, "修改成功");
    }

    /**
     * 修改规格值
     * @param
     * @return Result
     * @throws Exception 业务错误
     */
    @CrossOrigin
    @PostMapping(value = "api/updateSpecsValue")
    @ResponseBody
    public Result updateSpecsValue(@Valid @RequestBody ProductSkuValueDTO productSkuValueDTO) throws Exception {
        productSkuValueService.updateSkuValue(productSkuValueDTO);
        return ResultUtil.error(1, "修改成功");
    }
}
