package io.blg.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.blg.common.validator.group.AddGroup;
import io.blg.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * blg-security
 * 2018-12-20 16:25
 *
 * @author zhengWei
 **/
@Data
@TableName("blg_order")
public class OrderEntity {

    @TableId
    private long id;

    @NotBlank(message="订单编号不能为空")
    private String orderNo;

    @NotBlank(message="订单金额不能为空")
    private BigDecimal totalPrice;

    @NotBlank(message="下单日期不能为空")
    private Date orderDate;

    @NotBlank(message="订单状态不能为空")
    private String orderStatus;

    private Long userId;

    private long addressId;

    private String createName;

    private Date createDate;

    private String updateName;

    private Date updateDate;


}
