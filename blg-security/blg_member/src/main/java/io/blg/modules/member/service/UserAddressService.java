package io.blg.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.common.utils.PageUtils;
import io.blg.modules.member.entity.UserAddressEntity;

import java.util.HashMap;
import java.util.List;

/**
 * blg-security
 * 2019-03-09 17:29
 *
 * @author zhengWei
 * @describe
 **/
public interface UserAddressService extends IService<UserAddressEntity> {


    /**
     * 用户地址信息列表
     *
     * @param paramMap
     * @return
     */
    PageUtils queryPage(HashMap<String, Object> paramMap);

    /**
     * 用户地址信息保存
     *
     * @param userAddress
     */
    void save(UserAddressEntity userAddress);

    /**
     * 用户地址信息修改
     *
     * @param userAddress
     */
    void update(UserAddressEntity userAddress);

    /**
     * 根据用户ID获取用户地址
     * @return
     */
    UserAddressEntity findAddrByUser(Long userId);
}
