package com.springlearning.web;


import com.springlearning.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import javax.annotation.Resource;


@Controller("loginController")
public class LoginController
{
    private static Logger logger=Logger.getLogger(LoginController.class);

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "/index.html") //默认会 访问index.html页面
    public String loginPage()
    {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand)
    {
        String username=loginCommand.getUsername();
        String password=loginCommand.getPassword();
        boolean isvaliduser = userService.hasMatchUser(username, password);

        if (!isvaliduser)
        {
            return new ModelAndView("login", "error", "用户名或密码错误");
        }
        else
        {
            logger.info("username="+username+" password="+password);

            request.getSession().setAttribute("user", username);
            return new ModelAndView("main");

        }

    }

}
