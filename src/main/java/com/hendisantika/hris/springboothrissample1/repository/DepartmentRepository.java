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
 * Date: 2019-04-21
 * Time: 18:28
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    //get by managerID
    List<Department> getByManager(Employee e);
}
