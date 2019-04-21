package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.model.Department;
import com.hendisantika.hris.springboothrissample1.model.Employee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:36
 */
public interface EmployeeService {
    List<Employee> getAll();

    Employee getbyID(Long id);

    List<Employee> getByDepartment(Department d);

    void deleteEmployee(Employee emp);

    void deleteEmployee(Long ID);

    Employee saveOrUpdate(Employee e);
}