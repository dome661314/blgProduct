package io.blg.modules.prod.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.common.utils.DateUtils;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.Query;
import io.blg.modules.prod.dao.ProdBrandDao;
import io.blg.modules.prod.dao.ProductDao;
import io.blg.modules.prod.entity.ProdBrandEntity;
import io.blg.modules.prod.entity.ProductEntity;
import io.blg.modules.prod.service.ProdBrandService;
import io.blg.modules.prod.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * blg-security
 * 2019-01-21 14:15
 *
 * @author zhengWei
 * @describe 商品信息维护
 **/
@Service("prodBrandService")
public class ProdBrandServiceImpl extends ServiceImpl<ProdBrandDao, ProdBrandEntity> implements ProdBrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> paramMap) {
        String brandCode = (String) paramMap.get("brandCode");

        Page<ProdBrandEntity> page = this.selectPage(
                new Query<ProdBrandEntity>(paramMap).getPage(),
                new EntityWrapper<ProdBrandEntity>()
                        .like(StringUtils.isNotBlank(brandCode), "brand_code", brandCode)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProdBrandEntity product) {
        product.setCreateDate(new Date());
        this.insert(product);
    }

    @Override
    public void update(ProdBrandEntity product) {
        product.setUpdateDate(new Date());
        this.updateById(product);
    }
}
