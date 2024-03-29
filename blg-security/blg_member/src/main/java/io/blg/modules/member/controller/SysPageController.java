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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author zhengwei

 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
	
	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url;
	}

	@RequestMapping(value = {"/", "index.html"})
	public String index(){
		return "index";
	}

	@RequestMapping("index_new.html")
	public String indexNew(){
		return "index_new";
	}

	@RequestMapping("login.html")
	public String login(){
		return "login";
	}


	@RequestMapping("submit-order.html")
	public String address(){
		return "submit-order";
	}

	@RequestMapping("my-order.html")
	public String order(){
		return "my-order";
	}

	@RequestMapping("invoice-style.html")
	public String invoice(){
		return "invoice-style";
	}

	@RequestMapping("product-lists.html")
	public String product(){
		return "product-lists";
	}

	@RequestMapping("register.html")
	public String register(){
		return "register";
	}

	@RequestMapping("head.html")
	public String head(){
		return "head";
	}

	@RequestMapping("error.html")
	public String notFound(){
		return "error";
	}

	@RequestMapping("order-generated.html")
	public String generated(){
		return "order-generated";
	}


	@RequestMapping("order-detail.html")
	public String orderDetail(){
		return "order-detail";
	}

	@RequestMapping("phone-findpassword.html")
	public String password(){
		return "phone-findpassword";
	}



}
