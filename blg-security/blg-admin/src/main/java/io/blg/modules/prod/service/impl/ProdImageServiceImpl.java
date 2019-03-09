package io.blg.modules.prod.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.modules.prod.dao.ProdImageDao;
import io.blg.modules.prod.entity.ProdImageEntity;
import io.blg.modules.prod.service.ProdImageService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * blg-security
 * 2019-01-21 14:15
 *
 * @author zhengWei
 * @describe 商品图片信息维护
 **/
@Service("prodImageService")
public class ProdImageServiceImpl extends ServiceImpl<ProdImageDao, ProdImageEntity> implements ProdImageService {

    @Override
    public void save(ProdImageEntity prodImage) {
        prodImage.setCreateDate(new Date());
        this.save(prodImage);
    }
}
