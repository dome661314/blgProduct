package io.blg.modules.prod.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.common.annotation.DataFilter;
import io.blg.common.utils.DateUtils;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.Query;
import io.blg.modules.prod.dao.ProductDao;
import io.blg.modules.prod.entity.ProductEntity;
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
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryPage(Map<String, Object> paramMap) {
        String prodCode = (String) paramMap.get("prodCode");

        Page<ProductEntity> page = this.selectPage(
                new Query<ProductEntity>(paramMap).getPage(),
                new EntityWrapper<ProductEntity>()
                        .like(StringUtils.isNotBlank(prodCode), "prod_code", prodCode)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(ProductEntity product) {
        product.setCreateDate(new Date());
        if(StringUtils.isNotBlank(product.getProduceDateStr())){
            product.setProduceDate(DateUtils.stringToDate(product.getProduceDateStr(),"YYYY-MM-DD"));
        }
        this.insert(product);
    }

    @Override
    public void update(ProductEntity product) {
        product.setUpdateDate(new Date());
        this.updateById(product);
    }
}
