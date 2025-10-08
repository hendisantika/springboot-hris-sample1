package com.hendisantika.hris.springboothrissample1.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.hendisantika.hris.springboothrissample1.dto.DepartmentDTO;
import com.hendisantika.hris.springboothrissample1.dto.EmployeeDTO;
import com.hendisantika.hris.springboothrissample1.dto.JobDTO;
import com.hendisantika.hris.springboothrissample1.dto.UserDTO;
import com.hendisantika.hris.springboothrissample1.dto.UserSessionBean;
import com.hendisantika.hris.springboothrissample1.model.Employee;
import com.hendisantika.hris.springboothrissample1.model.Job;
import com.hendisantika.hris.springboothrissample1.repository.JobRepository;
import com.hendisantika.hris.springboothrissample1.service.DepartmentService;
import com.hendisantika.hris.springboothrissample1.service.EmployeeService;
import com.hendisantika.hris.springboothrissample1.service.MiscService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequiredArgsConstructor
public class HrController {
    private final EmployeeService employeeService;
    private final MiscService misc;
    private final DepartmentService deptServ;
    private final com.hendisantika.hris.springboothrissample1.service.UserService UserService;
    private final UserSessionBean currentUser;
    private final JobRepository jobRepository;

    @GetMapping("/login")
    String login(Model model) {
        return "login";
    }

    @PostMapping("/checklogin")
    String checkLogin(@ModelAttribute("user") UserDTO current, BindingResult b, Model model) {
        //try to return a user dto object given the password and username
        UserDTO newDTO = UserService.getUser(current.getUsername(), current.getPassword());
        if (newDTO != null) {
            this.currentUser.setUsername(current.getUsername());
            this.currentUser.setPassword(current.getPassword());
            return "redirect:/employees";
        } else {
            return "login";
        }

    }

    @ModelAttribute("user")
    public UserDTO getUserDto() {
        return new UserDTO();
    }

    @GetMapping("/")
    String index(Model model) {
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    String employeeList(Model model) {
        return "employees";
    }

    @GetMapping("/api/employees")
    @ResponseBody
    public String getAllEmployees() {
        List<EmployeeDTO> list = Lists.newArrayList();
        for (Employee e : employeeService.getAll()) {
            EmployeeDTO edto = new EmployeeDTO();

            // Handle department - set to 0 if null
            if (e.getDepartment() != null) {
                edto.setDepartmentId(e.getDepartment().getDepartmentId());
                edto.setDepartmentName(e.getDepartment().getDepartmentName());
            } else {
                edto.setDepartmentId(0L);
                edto.setDepartmentName("N/A");
            }

            // Set basic fields
            edto.setFirstName(e.getFirstName());
            edto.setLastName(e.getLastName());
            edto.setId(e.getEmployeeId());

            // Handle job - set to "N/A" if null
            if (e.getJob() != null && e.getJob().getJobTitle() != null) {
                edto.setJobTitle(e.getJob().getJobTitle());
            } else {
                edto.setJobTitle("N/A");
            }

            // Set action links
            edto.setDeleteLink("<a href='/delete?id=" + edto.getId() + "' "
                    + "class='btn btn-danger'>Delete</a>");
            edto.setUpdateLink("<a href='/update?id=" + edto.getId() + "' "
                    + "class='btn btn-success'>Update</a>");
            list.add(edto);
        }

        return new Gson().toJson(list);
    }

    @GetMapping("/create-new")
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
    @PostMapping("/create")
    String createNew(@ModelAttribute("employee") EmployeeDTO employee, BindingResult bindingResult, Model model) {

        Employee toSave = new Employee();

        // Fetch existing Job entity from database (proper relationship)
        Job j = jobRepository.findById(employee.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found: " + employee.getJobId()));

        toSave.setEmail("uzumaki_naruto@konohagakure.com");
        toSave.setHireDate(new Date());
        toSave.setFirstName(employee.getFirstName());
        toSave.setLastName(employee.getLastName());
        toSave.setJob(j);
        toSave.setSalary(employee.getSalary());
        toSave.setDepartment(deptServ.getOne(employee.getDepartmentId()));
        toSave.setPhoneNumber(employee.getPhoneNumber());

        this.employeeService.saveOrUpdate(toSave);
        return "redirect:/employees";
    }

    @GetMapping("/update")
    String update(@RequestParam("id") Long empId, Model model) {
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


    @PostMapping("/update-save")
    String updateSave(@ModelAttribute("employee") EmployeeDTO dto, BindingResult b, Model model) {
        createAndSave(dto);
        return "redirect:/employees";
    }

    private void createAndSave(EmployeeDTO emp) {
        //any fields that weren't changed will be same as the one in storage
        Employee toSave = this.employeeService.getbyID(emp.getId());

        // Fetch existing Job entity from database (proper relationship)
        Job j = jobRepository.findById(emp.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found: " + emp.getJobId()));

        //update the employee
        toSave.setFirstName(emp.getFirstName());
        toSave.setLastName(emp.getLastName());
        toSave.setPhoneNumber(emp.getPhoneNumber());
        toSave.setJob(j);
        toSave.setDepartment(this.deptServ.getOne(emp.getDepartmentId()));
        toSave.setSalary(emp.getSalary());
        this.employeeService.saveOrUpdate(toSave);
    }


    @GetMapping("/delete")
    String delete(@RequestParam("id") Long ID) {
        this.employeeService.deleteEmployee(ID);
        return "redirect:/employees";
    }

    @GetMapping("/jobs")
    String jobsList(Model model) {
        return "jobs";
    }

    @GetMapping("/api/jobs")
    @ResponseBody
    public String getAllJobs() {
        List<JobDTO> jobs = misc.getJobs();
        return new Gson().toJson(jobs);
    }

    @GetMapping("/departments")
    String departmentsList(Model model) {
        return "departments";
    }

    @GetMapping("/api/departments")
    @ResponseBody
    public String getAllDepartments() {
        List<DepartmentDTO> departments = deptServ.getDepartments();
        return new Gson().toJson(departments);
    }

    @GetMapping("/logout")
    String logout() {
        this.currentUser.setPassword(null);
        this.currentUser.setId(null);
        this.currentUser.setUsername(null);
        return "redirect:/login";
    }
}
