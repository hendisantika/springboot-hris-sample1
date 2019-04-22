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
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/update")
    String update(@RequestParam("id") Long empId, Model model) throws ParseException {
        Employee current = this.employeeService.getbyID(empId);
        EmployeeDTO emp = new EmployeeDTO();
        //populate fields
        emp.setId(current.getEmployeeId());
        emp.setFirstName(current.getFirstName());
        emp.setLastName(current.getLastName());
        emp.setPhoneNumber(current.getPhoneNumber());
        emp.setSalary(current.getSalary());
        emp.setDepartmentId(current.getDepartment().getDepartmentId());
        emp.setHireDate(current.getHireDate());
        emp.setJobTitle(current.getJob().getJobTitle());
        emp.setJobId(current.getJob().getJobId());
        emp.setEmail(current.getEmail());

        List<JobDTO> j = misc.getJobs();
        List<DepartmentDTO> d = deptServ.getDepartments();
        model.addAttribute("jobs", j);
        model.addAttribute("departments", d);
        model.addAttribute("employee", emp);
        return "update";
    }


    @ModelAttribute("employee")
    public EmployeeDTO getEmpDto() {
        return new EmployeeDTO();
    }


    @RequestMapping("/update-save")
    String updateSave(@ModelAttribute("employee") EmployeeDTO dto, BindingResult b, Model model) throws ParseException {
        createAndSave(dto);
        return "datatable-test";
    }

    private void createAndSave(EmployeeDTO emp) throws ParseException {
        //any fields that weren't changed will be same as the one in storage
        Employee toSave = this.employeeService.getbyID(emp.getId());
        //create job from job dto
        Job j = new Job();
        JobDTO setFrom = this.misc.getJobDTOByID(emp.getJobId());
        j.setJobId(setFrom.getJobId());
        j.setJobTitle(setFrom.getJobTitle());
        j.setMaxSalary(setFrom.getMaxSalary());
        j.setMinSalary(setFrom.getMinSalary());

        //create the new employee
        toSave.setFirstName(emp.getFirstName());
        toSave.setLastName(emp.getLastName());
        toSave.setPhoneNumber(emp.getPhoneNumber());
        toSave.setJob(j);
        toSave.setDepartment(this.deptServ.getOne(emp.getDepartmentId()));
        toSave.setSalary(emp.getSalary());
        this.employeeService.saveOrUpdate(toSave);
    }


    @RequestMapping("/delete")
    String delete(@RequestParam("id") Long ID) {
        this.employeeService.deleteEmployee(ID);
        return "redirect:/datatable-test";
    }
}
