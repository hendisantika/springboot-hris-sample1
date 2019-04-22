package com.hendisantika.hris.springboothrissample1.controller;

import com.hendisantika.hris.springboothrissample1.dto.UserSessionBean;
import com.hendisantika.hris.springboothrissample1.service.DepartmentService;
import com.hendisantika.hris.springboothrissample1.service.EmployeeService;
import com.hendisantika.hris.springboothrissample1.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-23
 * Time: 05:53
 */
@Controller
public class HrController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MiscService misc;

    @Autowired
    private DepartmentService deptServ;

    @Autowired
    private com.hendisantika.hris.springboothrissample1.service.UserService UserService;

    @Autowired
    private UserSessionBean currentUser;

    @RequestMapping(value = "/login")
    String login(Model model) {
        return "login";
    }
}
