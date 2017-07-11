package com.hand.controller;

import com.hand.entity.User;
import com.hand.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Controller
public class UserController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserController.class);

    @Resource
    UserService userService;

    /*
    * 查询全部用户
    * @param
    * @return listUser
    * */
    @Transactional
    @RequestMapping("/showUser")
    public String getUserList(HttpServletRequest request, Model model){
        try {
            List<User> userList = userService.listUser();
            model.addAttribute("userList",userList);
        } catch (Exception e) {
            logger.error("查询用户错误："+e);
        }
        return "showUser";
    }

    /*
    * 保存用户
    * @param user
    * return
    * */
    @Transactional
    @RequestMapping("/save")
    public void save(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("userName");
            int age = Integer.parseInt(request.getParameter("age"));
            String address = request.getParameter("address");
            User user = new User();
            user.setUserName(name);
            user.setAge(age);
            user.setAddress(address);
            userService.save(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/showUser");
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /*
    * 编辑用户
    * param id
    * return
    * */
    @Transactional
    @RequestMapping("/edit")
    public void getUser(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            String name = request.getParameter("userName");
            int age = Integer.parseInt(request.getParameter("age"));
            String address = request.getParameter("address");
            int id = Integer.parseInt(request.getParameter("id"));
            User user = new User();
            user.setUserName(name);
            user.setAge(age);
            user.setAddress(address);
            user.setId(id);
            userService.updateUser(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/showUser");
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /*
    * 删除用户
    * param id
    * return
    * */
    @Transactional
    @RequestMapping("/remove")
    public void remove(HttpServletRequest request, HttpServletResponse response, Model model){
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.remove(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/showUser");
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){
        if (!checkVerification(request,response)){
            model.addAttribute("msg","验证码错误！");
            return "index";
        }
        String name = request.getParameter("userName");
        User user = userService.checkLogin(name);
        if (user != null) {
            List<User> userList = userService.listUser();
            model.addAttribute("userList",userList);
            return "showUser";
        }
        model.addAttribute("msg","找不到用户！");
        return "index";
    }

    public boolean checkVerification(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String sessionCode = (String)session.getAttribute("randCheckCode");
        String checkCode = request.getParameter("checkCode");
        if (sessionCode.equals(checkCode)){
            return true;
        }
        return false;
    }
}
