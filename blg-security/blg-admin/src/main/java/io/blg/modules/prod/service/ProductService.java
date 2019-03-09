package io.blg.modules.prod.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.common.utils.PageUtils;
import io.blg.modules.prod.entity.ProductEntity;

import java.util.HashMap;

/**
 * blg-security
 * 2019-01-21 14:08
 *
 * @author zhengWei
 * @describe 商品信息管理
 **/
public interface ProductService extends IService<ProductEntity> {

    /**
     * 商品信息列表
     *
     * @param paramMap
     * @return
     */
    PageUtils queryPage(HashMap<String, Object> paramMap);

    /**
     * 商品信息保存
     *
     * @param product
     */
    void save(ProductEntity product);

    /**
     * 商品信息修改
     *
     * @param product
     */
    void update(ProductEntity product);

}
