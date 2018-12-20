package io.blg.modules.prod.controller;

import com.google.gson.Gson;
import io.blg.common.utils.Constant;
import io.blg.common.utils.R;
import io.blg.common.validator.ValidatorUtils;
import io.blg.common.validator.group.AliyunGroup;
import io.blg.common.validator.group.QcloudGroup;
import io.blg.common.validator.group.QiniuGroup;
import io.blg.modules.oss.cloud.CloudStorageConfig;
import io.blg.modules.prod.entity.Product;
import io.blg.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * blg-security
 * 2018-12-20 16:20
 * 商品信息维护
 *
 * @author zhengWei
 **/
@RestController
@RequestMapping("/prod")
public class ProductController extends AbstractController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);


    /**
     * 保存商品信息
     *
     * @param product
     * @return
     */
    @RequestMapping("/saveProduct")
    @RequiresPermissions("sys:prod:all")
    public R saveConfig(@RequestBody Product product) {
        //校验类型
        ValidatorUtils.validateEntity(product);

        return R.ok();
    }


}
