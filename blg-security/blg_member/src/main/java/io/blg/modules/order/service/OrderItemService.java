package io.blg.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.common.utils.PageUtils;
import io.blg.modules.order.entity.OrderEntity;
import io.blg.modules.order.entity.OrderItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * blg-security
 * 2019-01-21 14:08
 *
 * @author zhengWei
 * @describe 商品信息管理
 **/
public interface OrderItemService extends IService<OrderItemEntity> {


    /**
     * 根据订单ID查询订单明细
     *
     * @param id
     * @return
     */
    List<OrderItemEntity> selectItemList(long id);

    /**
     * 订单单身信息保存
     *
     * @param orderItemEntity
     */
    void save(OrderItemEntity orderItemEntity);

    /**
     * 通过订单ID获取订单单身信息
     * @param orderId
     * @return
     */
    OrderItemEntity getItemById(Long orderId);
}
