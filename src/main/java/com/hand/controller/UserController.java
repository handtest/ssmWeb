package com.hand.controller;

import com.hand.entity.User;
import com.hand.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by hanlu on 2017/7/6.
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
    public String getUserList(HttpServletRequest request, HttpSession session, Model model){
        logger.info(session.getAttribute("username")+"查询了用户列表");
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
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("userName");
            String age = request.getParameter("age");
            String address = request.getParameter("address");
            if (name != null && name != "" && age != null && age != "" && address != null && address != ""){
                User user = new User();
                user.setUserName(name);
                user.setAge(Integer.parseInt(age));
                user.setAddress(address);
                userService.save(user);
            }

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
    public void updateUser(HttpServletRequest request, HttpServletResponse response, Model model){
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
            String id = request.getParameter("id");
            if (id != null && id != ""){
                userService.remove(Integer.parseInt(id));
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/showUser");
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /*
    * 登录验证
    * param userName
    * param checkCode
    * return
    * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response,
                        Model model, @Param("name") String name,
                        @Param("checkCode")String checkCode){
        if (!checkVerification(request,response)){
            model.addAttribute("msg","验证码错误！");
            return "index";
        }
        if (name!=null && name != ""){
            User user = userService.checkLogin(name);
            if (user != null && !"admin".equals(user.getUserName())) {
                HttpSession session = request.getSession();
                Integer id = user.getId();
                session.setAttribute("name",name);
                session.setAttribute("id",id);
                List<User> userList = userService.listUser();
                model.addAttribute("userList",userList);
                return "page";
            }
            if (user.getUserName().equals("admin")) {
                HttpSession session = request.getSession();
                Integer id = user.getId();
                session.setAttribute("name",name);
                session.setAttribute("id",id);
                return "showLog";
            }
        }
        model.addAttribute("msg","找不到用户！");
        return "index";
    }

    /*
    * 验证码验证
    * */
    public boolean checkVerification(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String sessionCode = (String)session.getAttribute("randCheckCode");
        String checkCode = request.getParameter("checkCode");
        if (sessionCode == null){
            return false;
        }
        int equalsInt = sessionCode.compareToIgnoreCase(checkCode);
        if (equalsInt==0){
            return true;
        }
        return false;
    }
}
