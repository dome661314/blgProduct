package io.blg.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

/**
 * blg-security
 * 2019-02-13 16:36
 * 用户地址
 *
 * @author zhengWei
 * @describe
 **/
@Data
@TableName("blg_user_address")
public class UserAddressEntity {

    @TableId
    private long id;

    private long userId;

    private String userName;

    private String receiveName;

    private String receiveMobile;

    private String address;

    private String postalCode;

    private String province;

    private String city;

    private String county;

    private String createName;

    private Date createDate;

    private String updateName;

    private Date updateDate;

    private String isDefault;
}
