package io.blg.modules.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.Query;
import io.blg.modules.order.dao.OrderDao;
import io.blg.modules.order.entity.OrderEntity;
import io.blg.modules.order.service.OrderItemService;
import io.blg.modules.order.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * blg-security
 * 2019-01-21 14:15
 *
 * @author zhengWei
 * @describe 商品信息维护
 **/
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Resource
    private OrderItemService orderItemService;

    @Override
    public PageUtils queryPage(HashMap<String, Object> paramMap) {
        String username = (String) paramMap.get("username");

        Page<OrderEntity> page = this.selectPage(
                new Query<OrderEntity>(paramMap).getPage(),
                new EntityWrapper<OrderEntity>()
                        .like(StringUtils.isNotBlank(username), "username", username)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OrderEntity order) {
        order.setCreateDate(new Date());
        boolean result = this.insert(order);
        if (result && !order.getItemList().isEmpty()) {
            //保存订单单身信息
            orderItemService.insertBatch(order.getItemList());
        }
    }

    @Override
    public void update(OrderEntity order) {
        order.setUpdateDate(new Date());
        this.updateById(order);
    }

}
