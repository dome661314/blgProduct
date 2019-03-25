package io.blg.modules.prod.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.modules.prod.entity.ProdImageEntity;

/**
 * blg-security
 * 2019-01-21 14:08
 *
 * @author zhengWei
 * @describe 商品图片信息管理
 **/
public interface ProdImageService extends IService<ProdImageEntity> {

    /**
     * 商品信息保存
     *
     * @param prodImage
     */
    void save(ProdImageEntity prodImage);

}
