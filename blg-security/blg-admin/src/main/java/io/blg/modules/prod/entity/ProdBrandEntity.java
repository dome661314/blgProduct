package io.blg.modules.prod.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * blg-security
 * 2018-12-20 16:25
 *
 * @author zhengWei
 **/
@Data
@TableName("blg_prod_brand")
public class ProdBrandEntity {

    @TableId
    private long id;

    @NotBlank(message="商品编码不能为空")
    private String brandCode;

    @NotBlank(message="商品名称不能为空")
    private String brandName;


    private String createName;

    private Date createDate;

    private String updateName;

    private Date updateDate;


}
