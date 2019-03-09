package io.blg.modules.sys.controller;

import io.blg.common.annotation.SysLog;
import io.blg.common.utils.PageUtils;
import io.blg.common.utils.R;
import io.blg.common.validator.ValidatorUtils;
import io.blg.modules.sys.entity.UserAddressEntity;
import io.blg.modules.sys.service.UserAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;


/**
 * blg-security
 * 2018-12-20 16:20
 * 商品信息维护
 *
 * @author zhengWei
 **/
@RestController
@RequestMapping("/blg/userAddress")
public class UserAddrController extends AbstractController {

    @Autowired
    private UserAddressService userAddressService;


    /**
     * 保存商品信息
     *
     * @param userAddress
     * @return
     */
    @SysLog("保存用户地址信息")
    @RequestMapping("/save")
    @RequiresPermissions("sys:userAddress:save")
    public R save(@RequestBody UserAddressEntity userAddress) {
        //校验类型
        ValidatorUtils.validateEntity(userAddress);
        userAddressService.save(userAddress);
        return R.ok();
    }


    /**
     * 查看用户地址列表
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:userAddress:list")
    public R list() {
        PageUtils page = userAddressService.queryPage(new HashMap<>());
        return R.ok().put("page", page);
    }


    /**
     * 修改用户地址信息
     *
     * @param userAddress
     * @return
     */
    @SysLog("修改用户地址信息")
    @RequestMapping("/update")
    @RequiresPermissions("sys:userAddress:update")
    public R update(@RequestBody UserAddressEntity userAddress) {
        //校验类型
        ValidatorUtils.validateEntity(userAddress);
        userAddressService.update(userAddress);
        return R.ok();
    }


    /**
     * 删除用户地址信息
     *
     * @param userAddressIds
     * @return
     */
    @SysLog("删除用户地址信息")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:userAddress:delete")
    public R delete(@RequestBody Long[] userAddressIds) {
        userAddressService.deleteBatchIds(Arrays.asList(userAddressIds));
        return R.ok();
    }


    @SysLog("查看用户地址详细信息")
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:userAddress:info")
    public R info(@PathVariable("id") Long id){
        UserAddressEntity userAddress = userAddressService.selectById(id);
        return R.ok().put("userAddress", userAddress);
    }


}
