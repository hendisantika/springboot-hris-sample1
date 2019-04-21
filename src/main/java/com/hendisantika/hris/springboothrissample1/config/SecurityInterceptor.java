package com.hendisantika.hris.springboothrissample1.config;

import com.hendisantika.hris.springboothrissample1.dto.UserSessionBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 18:20
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserSessionBean usb;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String p = request.getRequestURI();
        String d = request.getRequestURI();

        if (!StringUtils.equals("/login", request.getRequestURI()) && !StringUtils.equals("/checklogin", request.getRequestURI())) {

            if (this.usb.getUsername() != null && this.usb.getPassword() != null) {
                return true;
            } else {
                request.getRequestDispatcher("/login").forward(request, response);
                return false;
            }
        } else {
            return true;
        }
    }
}