package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by caojingchen on 2018/1/23.
 */
@Controller
public class MainController {
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/doLogin")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            UserInfo userInfo =(UserInfo) subject.getPrincipal();
            session.setAttribute("username",userInfo.getUsername());
            return "index";
        }catch (UnknownAccountException ex1){
            System.out.println("账号不存在");
            return "403";
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            return "403";
        } catch (LockedAccountException e) {
            System.out.println("登录失败，该用户已被冻结");
            return "403";
        } catch (AuthenticationException e) {
            System.out.println("该用户不存在");
            return "403";
        } catch (Exception e) {
            e.printStackTrace();
            return "403";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        session.removeAttribute("username");
        subject.logout();
        return "login";
    }

    @RequestMapping(value = "userAdd")
    @RequiresPermissions("userInfo:add")
    public String userAdd(){
        return "userAdd";
    }
}
