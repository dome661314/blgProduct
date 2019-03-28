/**
 * Copyright 2018 人人开源 http://www.blg.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.blg.modules.member.controller;


import io.blg.common.utils.OrderCodeFactory;
import io.blg.common.utils.R;
import io.blg.modules.member.entity.SysUserEntity;
import io.blg.modules.member.entity.UserAddressEntity;
import io.blg.modules.member.service.SysUserService;
import io.blg.modules.member.service.UserAddressService;
import io.blg.modules.member.shiro.ShiroUtils;
import io.blg.modules.order.entity.OrderEntity;
import io.blg.modules.order.entity.OrderItemEntity;
import io.blg.modules.order.service.OrderItemService;
import io.blg.modules.order.service.OrderService;
import io.blg.modules.prod.entity.ProductEntity;
import io.blg.modules.prod.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 系统用户
 * 
 * @author zhengwei

 * @date 2016年10月31日 上午10:40:10
 */
@Controller
public class MemberController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService orderItemService;

	/**
	 * 所有产品信息
	 */
	@ResponseBody
	@RequestMapping(value = "/member/index", method = RequestMethod.POST)
	public Object index(){
		List<ProductEntity> prodList = productService.selectList(null);
		return prodList;
	}

	/**
	 * 获取单个产品信息
	 */
	@RequestMapping(value = "/prodDetail", method = RequestMethod.GET)
	public String detail(Long id,ModelMap map){
		ProductEntity prod = productService.selectById(id);
		map.addAttribute("prod",prod);
		return "product-lists.html";
	}


	/**
	 * 保存用户
	 */
	@ResponseBody
	@RequestMapping(value = "/member/saveMember", method = RequestMethod.POST)
	public R save(String username,String mobile,String password,String email){
		SysUserEntity user =new SysUserEntity();
		user.setUsername(username);
		user.setMobile(mobile);
		user.setPassword(password);
		user.setEmail(email);
		//会员用户默认都是这个部门
		user.setDeptId(6l);
		//有效
		user.setStatus(1);
		sysUserService.save(user);
		return R.ok();
	}

	/**
	 * 轻松租
	 */
	@RequestMapping(value = "/rent", method = RequestMethod.GET)
	public String rent(Long prodId,ModelMap map){
		ProductEntity prod = productService.selectById(prodId);
		SysUserEntity user = ShiroUtils.getUserEntity();
		UserAddressEntity userAddressEntity = userAddressService.findAddrByUser(Long.valueOf(user.getUserId()));
		map.addAttribute("userAddr",userAddressEntity);
		map.addAttribute("user",user);
		map.addAttribute("prod",prod);
		return "invoice-style.html";
	}

	/**
	 * 保存用户地址
	 */
	@ResponseBody
	@RequestMapping(value = "/member/addUserAddr", method = RequestMethod.POST)
	public R save(String receiveName, String receiveMobile, String address, String postalCode, String province, String city, String county,String userId,String username){
		UserAddressEntity userAddressEntity =new UserAddressEntity();
		userAddressEntity.setCreateDate(new Date());
		userAddressEntity.setAddress(address);
		userAddressEntity.setCity(city);
		userAddressEntity.setCounty(county);
		userAddressEntity.setPostalCode(postalCode);
		userAddressEntity.setProvince(province);
		userAddressEntity.setReceiveMobile(receiveMobile);
		userAddressEntity.setUserId(Long.valueOf(userId));
		userAddressEntity.setUserName(username);
		userAddressEntity.setReceiveName(receiveName);
		userAddressEntity.setIsDefault("0");
		userAddressService.save(userAddressEntity);
		return R.ok();
	}


	/**
	 * 获取用户地址信息
	 */
	@ResponseBody
	@RequestMapping(value = "/member/findUserAddr", method = RequestMethod.POST)
	public Object findUserAddr(String userId){
		UserAddressEntity userAddr = userAddressService.findAddrByUser(Long.valueOf(userId));
		return userAddr;
	}


	@ResponseBody
	@RequestMapping(value = "/member/createOrder", method = RequestMethod.POST)
	public Map<String,Object> createOrder(String userId, String prodId, String dayNum, String startDate, String endDate, String addressId) throws ParseException {
		Map<String,Object> dataMap = new HashMap<>();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
		ProductEntity prod = productService.selectById(Long.valueOf(prodId));
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderDate(new Date());
		orderEntity.setOrderNo(OrderCodeFactory.getOrderCode());
		orderEntity.setOrderStatus("1");
		orderEntity.setUserId(Long.valueOf(userId));
		orderEntity.setTotalPrice(prod.getDeposit().add(prod.getProdPrice().multiply(new BigDecimal(dayNum))));
		orderEntity.setAddressId(Long.valueOf(addressId));
		boolean result = orderService.save(orderEntity);
		if(result) {
			OrderItemEntity orderItemEntity = new OrderItemEntity();
			orderItemEntity.setOrderId(orderEntity.getId());
			orderItemEntity.setProdId(Long.valueOf(prodId));
			orderItemEntity.setProdName(prod.getProdName());
			orderItemEntity.setProdNum(1);
			orderItemEntity.setProdPrice(prod.getProdPrice());
			orderItemEntity.setRentStartDate(format.parse(startDate));
			orderItemEntity.setRentEndDate(format.parse(endDate));
			orderItemService.save(orderItemEntity);
		}
		dataMap.put("id",orderEntity.getId());
		return dataMap;
	}

	@RequestMapping(value = "/generated", method = RequestMethod.GET)
	public String generated(Long orderId,ModelMap map){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		OrderEntity orderEntity = orderService.selectById(orderId);
		SysUserEntity user = ShiroUtils.getUserEntity();
		map.addAttribute("order",orderEntity);
		map.addAttribute("user",user);
		map.addAttribute("orderDate",format.format(orderEntity.getOrderDate()));
		return "order-generated.html";
	}


	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
	public String orderDetail(Long orderId,ModelMap map){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		OrderEntity orderEntity = orderService.selectById(orderId);
		OrderItemEntity orderItemEntity = orderItemService.getItemById(orderId);
		SysUserEntity user = ShiroUtils.getUserEntity();
		UserAddressEntity userAddr = userAddressService.findAddrByUser(user.getUserId());
		ProductEntity productEntity = productService.selectById(orderItemEntity.getProdId());
		map.addAttribute("order",orderEntity);
		map.addAttribute("user",user);
		map.addAttribute("item",orderItemEntity);
		map.addAttribute("orderDate",format.format(new Date()));
		map.addAttribute("userAddr",userAddr);
		map.addAttribute("prod",productEntity);
		map.addAttribute("startDate",format.format(orderItemEntity.getRentStartDate()));
		map.addAttribute("endDate",format.format(orderItemEntity.getRentEndDate()));
		return "order-detail.html";
	}

	@ResponseBody
	@RequestMapping(value = "/member/cancelOrder", method = RequestMethod.POST)
	public R cancelOrder(Long id){
		OrderEntity orderEntity =orderService.selectById(id);
		if(orderEntity!=null){
			orderEntity.setOrderStatus("-1");
			orderService.update(orderEntity);
		}else{
			R.error("订单取消失败");
		}
		return R.ok();
	}

	@ResponseBody
	@RequestMapping(value = "/member/payOrder", method = RequestMethod.POST)
	public R payOrder(Long id){
		OrderEntity orderEntity =orderService.selectById(id);
		if(orderEntity!=null){
			orderEntity.setOrderStatus("2");
			orderService.update(orderEntity);
		}else{
			R.error("订单支付失败");
		}
		return R.ok();
	}

}
