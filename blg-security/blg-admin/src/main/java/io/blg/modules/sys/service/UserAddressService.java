package io.blg.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.blg.common.utils.PageUtils;
import io.blg.modules.prod.entity.ProductEntity;
import io.blg.modules.sys.entity.UserAddressEntity;

import java.util.HashMap;

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
}
