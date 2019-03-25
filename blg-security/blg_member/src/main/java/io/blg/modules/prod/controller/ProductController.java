package io.blg.modules.prod.controller;

import io.blg.common.annotation.SysLog;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.R;
import io.blg.common.validator.ValidatorUtils;
import io.blg.modules.prod.entity.ProductEntity;
import io.blg.modules.prod.service.ProductService;
import io.blg.modules.member.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;


/**
 * blg-security
 * 2018-12-20 16:20
 * 商品信息维护
 *
 * @author zhengWei
 **/
@RestController
@RequestMapping("/blg/prod")
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;


    /**
     * 保存商品信息
     *
     * @param product
     * @return
     */
    @SysLog("保存商品信息")
    @RequestMapping("/save")
    @RequiresPermissions("blg:prod:save")
    public R save(@RequestBody ProductEntity product) {
        //校验类型
        ValidatorUtils.validateEntity(product);
        productService.save(product);
        return R.ok();
    }


    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("blg:prod:list")
    public R list() {
        PageUtils page = productService.queryPage(new HashMap<>());
        return R.ok().put("page", page);
    }


    /**
     * 修改商品信息
     *
     * @param product
     * @return
     */
    @SysLog("修改商品信息")
    @RequestMapping("/update")
    @RequiresPermissions("blg:prod:update")
    public R update(@RequestBody ProductEntity product) {
        //校验类型
        ValidatorUtils.validateEntity(product);
        productService.update(product);
        return R.ok();
    }


    /**
     * 删除商品信息
     *
     * @param prodIds
     * @return
     */
    @SysLog("删除商品信息")
    @RequestMapping("/delete")
    @RequiresPermissions("blg:prod:delete")
    public R delete(@RequestBody Long[] prodIds) {
        productService.deleteBatchIds(Arrays.asList(prodIds));
        return R.ok();
    }


    @SysLog("查看商品详细信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("blg:prod:info")
    public R info(@PathVariable("id") Long id){
        ProductEntity prod = productService.selectById(id);
        return R.ok().put("prod", prod);
    }


}
