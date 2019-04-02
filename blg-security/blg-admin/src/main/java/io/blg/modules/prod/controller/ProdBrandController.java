package io.blg.modules.prod.controller;

import io.blg.common.annotation.SysLog;
import io.blg.common.exception.RRException;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.R;
import io.blg.common.validator.ValidatorUtils;
import io.blg.modules.prod.entity.ProdBrandEntity;
import io.blg.modules.prod.service.ProdBrandService;
import io.blg.modules.prod.service.ProductService;
import io.blg.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * blg-security
 * 2018-12-20 16:20
 * 品牌信息维护
 *
 * @author zhengWei
 **/
@RestController
@RequestMapping("/blg/prodBrand")
public class ProdBrandController extends AbstractController {

    @Autowired
    private ProdBrandService prodBrandService;


    /**
     * 保存品牌信息
     *
     * @param product
     * @return
     */
    @SysLog("保存品牌信息")
    @RequestMapping("/save")
    @RequiresPermissions("blg:prodBrand:save")
    public R save(@RequestBody ProdBrandEntity product) {
        //校验类型
        ValidatorUtils.validateEntity(product);
        prodBrandService.save(product);
        return R.ok();
    }


    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("blg:prodBrand:list")
    public R list(Map<String, Object> param) {
        PageUtils page = prodBrandService.queryPage(param);
        return R.ok().put("page", page);
    }


    /**
     * 修改品牌信息
     *
     * @param product
     * @return
     */
    @SysLog("修改品牌信息")
    @RequestMapping("/update")
    @RequiresPermissions("blg:prodBrand:update")
    public R update(@RequestBody ProdBrandEntity product) {
        //校验类型
        ValidatorUtils.validateEntity(product);
        prodBrandService.update(product);
        return R.ok();
    }


    /**
     * 删除品牌信息
     *
     * @param prodIds
     * @return
     */
    @SysLog("删除品牌信息")
    @RequestMapping("/delete")
    @RequiresPermissions("blg:prodBrand:delete")
    public R delete(@RequestBody Long[] prodIds) {
        prodBrandService.deleteBatchIds(Arrays.asList(prodIds));
        return R.ok();
    }


    @SysLog("查看商品详细信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("blg:prodBrand:info")
    public R info(@PathVariable("id") Long id){
        ProdBrandEntity prod = prodBrandService.selectById(id);
        return R.ok().put("prod", prod);
    }


}
