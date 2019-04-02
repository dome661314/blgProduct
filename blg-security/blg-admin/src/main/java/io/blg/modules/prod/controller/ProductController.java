package io.blg.modules.prod.controller;

import io.blg.common.annotation.SysLog;
import io.blg.common.exception.RRException;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.R;
import io.blg.common.validator.ValidatorUtils;
import io.blg.modules.oss.cloud.OSSFactory;
import io.blg.modules.oss.entity.SysOssEntity;
import io.blg.modules.prod.entity.ProductEntity;
import io.blg.modules.prod.service.ProductService;
import io.blg.modules.sys.controller.AbstractController;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    private final static String IMG_PATH="/Users/dome/Documents/zw/lunwen/blgProduct/blg-security/blg_member/src/main/resources/statics/images";

    private final static String URL="http://localhost:8081/blg-member/statics/images";


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
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = productService.queryPage(params);
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


    @ResponseBody
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        File dir = new File(IMG_PATH);
        if(!dir.exists()){
            dir.mkdir();
        }
        String path =dir+"/"+file.getOriginalFilename();
        file.transferTo(new File(path));
        //返回访问路径
        String url = URL + "/" +file.getOriginalFilename();
        return R.ok().put("url", url);
    }

}
