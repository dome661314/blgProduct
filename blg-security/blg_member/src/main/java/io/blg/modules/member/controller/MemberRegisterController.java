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


import io.blg.modules.member.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注册相关
 *
 * @author zhengwei
 * @date 2019年03月18日 下午1:15:31
 */
@Controller
public class MemberRegisterController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 注册-验证用户名是否存在
     */
    @ResponseBody
    @RequestMapping(value = "/member/validate", method = RequestMethod.POST)
    public boolean login(String username) {
        return sysUserService.validate(username);
    }

}