package com.hendisantika.hris.springboothrissample1.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.hendisantika.hris.springboothrissample1.dto.*;
import com.hendisantika.hris.springboothrissample1.model.Employee;
import com.hendisantika.hris.springboothrissample1.model.Job;
import com.hendisantika.hris.springboothrissample1.service.DepartmentService;
import com.hendisantika.hris.springboothrissample1.service.EmployeeService;
import com.hendisantika.hris.springboothrissample1.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

    @RequestMapping("/checklogin")
    String checkLogin(@ModelAttribute("user") UserDTO current, BindingResult b, Model model) throws ParseException {
        //try to return a user dto object given the password and username
        UserDTO newDTO = UserService.getUser(current.getUsername(), current.getPassword());
        if (newDTO != null) {
            this.currentUser.setUsername(current.getUsername());
            this.currentUser.setPassword(current.getPassword());
            return "redirect:/datatable-test"; //table
        } else {
            return "login";
        }

    }

    @ModelAttribute("user")
    public UserDTO getUserDto() {
        return new UserDTO();
    }

    @RequestMapping("/")
    String index(Model model) {
        return "redirect:/datatable-test";
    }

    @RequestMapping("/datatable-test")
    String read(Model model) {
        return "datatable-test";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public String getAllEmployees() {
        List<EmployeeDTO> list = Lists.newArrayList();
        for (Employee e : employeeService.getAll()) {
            EmployeeDTO edto = new EmployeeDTO();
            if (e.getDepartment() != null) {
                edto.setDepartmentId(e.getDepartment().getDepartmentId());
            } else {
                edto.setDepartmentId((long) 000);
            }
            edto.setFirstName(e.getFirstName());
            edto.setLastName(e.getLastName());
            edto.setJobTitle(e.getJob().getJobTitle());
            edto.setId(e.getEmployeeId());
            edto.setDeleteLink("<a href='/delete?id=" + edto.getId() + "' "
                    + "class='btn btn-danger'>Delete</a>");
            edto.setUpdateLink("<a href='/update?id=" + edto.getId() + "' "
                    + "class='btn btn-success'>Update</a>");
            list.add(edto);
        }

        String jsonString = new Gson().toJson(list);
        return jsonString;
    }

    @RequestMapping("/create-new")
    String create(Model model) {
        //add jobs, and departments
        List<JobDTO> j = misc.getJobs();
        List<DepartmentDTO> d = deptServ.getDepartments();
        model.addAttribute("jobs", j);
        model.addAttribute("departments", d);
        return "create-new";
    }

    @ModelAttribute("employee")
    public EmployeeDTO initEmployee() {
        return new EmployeeDTO();
    }

    //after user enters info about new employee, data transferred here to be created
    //@ModelAttribute(employee) = th:object=${employee}
    @RequestMapping("/create")
    String createNew(@ModelAttribute("employee") EmployeeDTO employee, BindingResult bindingResult, Model model) throws ParseException {

        Employee toSave = new Employee();
        Job j = new Job();
        JobDTO dto = misc.getJobDTOByID(employee.getJobId());

        j.setJobId(employee.getJobId());
        j.setJobTitle(employee.getJobTitle());
        j.setMaxSalary(dto.getMaxSalary());
        j.setMinSalary(dto.getMinSalary());

        toSave.setEmail("uzumaki_naruto@konohagakure.com");
        toSave.setHireDate(new Date());
        toSave.setFirstName(employee.getFirstName());
        toSave.setLastName(employee.getLastName());
        toSave.setJob(j);
        toSave.setSalary(employee.getSalary());
        toSave.setDepartment(deptServ.getOne(employee.getDepartmentId()));
        toSave.setPhoneNumber(employee.getPhoneNumber());

        this.employeeService.saveOrUpdate(toSave);
        return "redirect:/datatable-test";
    }

}
