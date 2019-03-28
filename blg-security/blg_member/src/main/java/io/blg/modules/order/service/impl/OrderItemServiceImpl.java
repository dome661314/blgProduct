package io.blg.modules.order.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.modules.order.dao.OrderItemDao;
import io.blg.modules.order.entity.OrderItemEntity;
import io.blg.modules.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * blg-security
 * 2019-01-21 14:15
 *
 * @author zhengWei
 * @describe 订单明细信息维护
 **/
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public List<OrderItemEntity> selectItemList(long id) {
        return null;
    }

    @Override
    public void save(OrderItemEntity orderItemEntity) {
        orderItemEntity.setCreateDate(new Date());
        this.insert(orderItemEntity);
    }

    @Override
    public OrderItemEntity getItemById(Long orderId) {
        return orderItemDao.selectItemById(orderId);
    }
}
