package com.scu275.invoicemanagement.config;

import com.alibaba.fastjson2.JSON;
import com.scu275.invoicemanagement.common.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class LoginAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.OK.value());

        Result res = new Result<>();
        res.setCode("1001");
        res.setMsg("Access denied");

        response.getWriter().write(JSON.toJSONString(res));
    }
}
