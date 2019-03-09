package io.blg.modules.order.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.modules.order.dao.OrderItemDao;
import io.blg.modules.order.entity.OrderItemEntity;
import io.blg.modules.order.service.OrderItemService;
import org.springframework.stereotype.Service;

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


    @Override
    public List<OrderItemEntity> selectItemList(long id) {
        return null;
    }
}
