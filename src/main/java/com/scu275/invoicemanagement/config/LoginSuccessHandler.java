package com.scu275.invoicemanagement.config;

import com.alibaba.fastjson2.JSON;
import com.scu275.invoicemanagement.common.result.Result;
import com.scu275.invoicemanagement.common.result.ResultCode;
import com.scu275.invoicemanagement.entity.User;
import com.scu275.invoicemanagement.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
        User userDetails = (User) authentication.getPrincipal();

        HttpSession session = request.getSession();
        //第二步：将想要保存到数据存入session中
        session.setAttribute("uid",userDetails.getUId());




        Result res = new Result<>();
        res.setCode("0000");
        res.setMsg("login success");
        res.setData(userDetails);

        response.getWriter().write(JSON.toJSONString(res));
    }
}
