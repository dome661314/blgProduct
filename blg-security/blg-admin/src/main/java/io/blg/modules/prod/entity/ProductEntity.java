package io.blg.modules.prod.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@TableName("blg_product")
public class ProductEntity {

    @TableId
    private long id;

    @NotBlank(message="商品编码不能为空")
    private String prodCode;

    @NotBlank(message="商品名称不能为空")
    private String prodName;

    @NotBlank(message="商品规格不能为空")
    private String prodSpec;

    @NotNull(message="商品价格不能为空")
    private BigDecimal prodPrice;

    @NotBlank(message="商品单位不能为空")
    private String prodUnit;

    private String prodStatus;

    @TableField(exist=false)
    private List<ProdImageEntity> imageList = null;

    private String createName;

    private Date createDate;

    private String updateName;

    private Date updateDate;





}
