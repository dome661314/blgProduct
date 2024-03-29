package io.blg.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.blg.modules.member.entity.UserAddressEntity;

import java.util.List;

/**
 * blg-security
 * 2019-03-09 17:34
 *
 * @author zhengWei
 * @describe
 **/
public interface UserAddressDao extends BaseMapper<UserAddressEntity> {

    UserAddressEntity selectAddrByUser(Long userId);
}
