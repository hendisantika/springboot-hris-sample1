package com.hendisantika.hris.springboothrissample1.repository;

import com.hendisantika.hris.springboothrissample1.model.Department;
import com.hendisantika.hris.springboothrissample1.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:30
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    //get by last name
    List<Employee> getByLastName(String lastname);

    //get by department id
    List<Employee> getByDepartment(Department d);

    //get by manager id
    List<Employee> getByManager(Employee e);
}
