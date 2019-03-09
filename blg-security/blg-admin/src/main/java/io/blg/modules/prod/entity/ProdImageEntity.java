package io.blg.modules.prod.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * blg-security
 * 2019-02-13 16:31
 *
 * @author zhengWei
 * @describe 商品图片
 **/
@Data
@TableName("blg_prod_image")
public class ProdImageEntity {

    @TableId
    private long id;

    private long prodId;

    private String url;

    private String type;

    private String createName;

    private Date createDate;

}
