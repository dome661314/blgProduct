package io.blg.modules.prod.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.common.utils.PageUtils;
import io.blg.modules.prod.entity.ProdBrandEntity;

import java.util.Map;

public interface ProdBrandService extends IService<ProdBrandEntity> {


    /**
     * 品牌信息列表
     *
     * @param paramMap
     * @return
     */
    PageUtils queryPage(Map<String, Object> paramMap);

    /**
     * 品牌信息保存
     *
     * @param prodBrand
     */
    void save(ProdBrandEntity prodBrand);

    /**
     * 品牌信息修改
     *
     * @param prodBrand
     */
    void update(ProdBrandEntity prodBrand);


}
