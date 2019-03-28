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


import io.blg.common.utils.R;
import io.blg.modules.member.dao.SysUserDao;
import io.blg.modules.member.entity.SysUserEntity;
import io.blg.modules.member.shiro.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录相关
 *
 * @author zhengwei
 * @date 2019年03月18日 下午1:15:31
 */
@Controller
public class MemberLoginController {

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/member/login", method = RequestMethod.POST)
    public R login(String username, String password) {
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        } catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }

        return R.ok();
    }

    @ResponseBody
    @RequestMapping(value = "/member/fetchUserHead", method = RequestMethod.POST)
    public SysUserEntity login() {
        SysUserEntity user = ShiroUtils.getUserEntity();
        return user;
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }

}
