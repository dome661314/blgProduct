package io.blg.modules.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.blg.modules.order.entity.OrderItemEntity;

import java.util.List;

/**
 * blg-security
 * 2019-02-13 14:10
 *
 * @author zhengWei
 * @describe
 **/
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {


    /**
     * 根据订单ID查找订单明细
     * @param id
     * @return
     */
    List<OrderItemEntity> queryListByOrderId(long id);

}
