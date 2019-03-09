package io.blg.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * blg-security
 * 2018-12-20 16:25
 *
 * @author zhengWei
 **/
@Data
@TableName("blg_order_item")
public class OrderItemEntity {

    @TableId
    private long id;

    private long orderId;

    private long prodId;

    private String prodName;

    @NotBlank(message="商品数量不能为空")
    private int prodNum;

    @NotBlank(message="商品单价不能为空")
    private BigDecimal prodPrice;

    @NotBlank(message="租赁开始时间不能为空")
    private Date rentStartDate;

    @NotBlank(message="租赁结束时间不能为空")
    private Date rentEndDate;

    private String createName;

    private Date createDate;

    private String updateName;

    private Date updateDate;


}
