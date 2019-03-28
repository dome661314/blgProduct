package io.blg.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.common.utils.PageUtils;
import io.blg.modules.order.entity.OrderEntity;

import java.util.HashMap;

/**
 * blg-security
 * 2019-01-21 14:08
 *
 * @author zhengWei
 * @describe 商品信息管理
 **/
public interface OrderService extends IService<OrderEntity> {

    /**
     * 订单信息列表
     *
     * @param paramMap
     * @return
     */
    PageUtils queryPage(HashMap<String, Object> paramMap);

    /**
     * 订单信息保存
     *
     * @param product
     */
    boolean save(OrderEntity product);

    /**
     * 订单信息修改
     *
     * @param product
     */
    void update(OrderEntity product);

}
