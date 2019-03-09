package io.blg.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.Query;
import io.blg.modules.prod.entity.ProductEntity;
import io.blg.modules.sys.dao.UserAddressDao;
import io.blg.modules.sys.entity.UserAddressEntity;
import io.blg.modules.sys.service.UserAddressService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * blg-security
 * 2019-03-09 17:29
 *
 * @author zhengWei
 * @describe
 **/
@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressDao, UserAddressEntity> implements UserAddressService {


    @Override
    public PageUtils queryPage(HashMap<String, Object> paramMap) {
        String userName = (String) paramMap.get("userName");
        Page<UserAddressEntity> page = this.selectPage(
                new Query<UserAddressEntity>(paramMap).getPage(),
                new EntityWrapper<UserAddressEntity>()
                        .like(StringUtils.isNotBlank(userName), "userName", userName)
        );

        return new PageUtils(page);
    }

    @Override
    public void save(UserAddressEntity userAddress) {
        userAddress.setCreateDate(new Date());
        this.insert(userAddress);
    }

    @Override
    public void update(UserAddressEntity userAddress) {
        userAddress.setUpdateDate(new Date());
        this.updateById(userAddress);
    }
}
