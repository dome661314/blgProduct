package io.blg.modules.order.controller;

import io.blg.common.annotation.SysLog;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.R;
import io.blg.common.validator.ValidatorUtils;
import io.blg.modules.order.entity.OrderEntity;
import io.blg.modules.order.entity.OrderItemEntity;
import io.blg.modules.order.service.OrderItemService;
import io.blg.modules.order.service.OrderService;
import io.blg.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * blg-security
 * 2018-12-20 16:20
 * 订单信息维护
 *
 * @author zhengWei
 **/
@RestController
@RequestMapping("/blg//order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    /**
     * 保存商品信息
     *
     * @param order
     * @return
     */
    @SysLog("保存订单信息")
    @RequestMapping("/save")
    @RequiresPermissions("blg:order:save")
    public R save(@RequestBody OrderEntity order) {
        //校验类型
        ValidatorUtils.validateEntity(order);
        orderService.save(order);
        return R.ok();
    }


    /**
     * 订单信息列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("blg:order:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 修改订单信息
     *
     * @param order
     * @return
     */
    @SysLog("修改订单信息")
    @RequestMapping("/update")
    @RequiresPermissions("blg:order:update")
    public R update(@RequestBody OrderEntity order) {
        //校验类型
        ValidatorUtils.validateEntity(order);
        orderService.update(order);
        return R.ok();
    }


    @SysLog("查看订单信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("blg:order:info")
    public R info(@PathVariable("id") Long id){
        OrderEntity order = orderService.selectById(id);
        return R.ok().put("order", order);
    }

}
