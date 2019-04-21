package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.model.Department;
import com.hendisantika.hris.springboothrissample1.model.Employee;
import com.hendisantika.hris.springboothrissample1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;


    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return (List<Employee>) empRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getbyID(Long id) {
        return empRepo.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getByDepartment(Department d) {
        return empRepo.getByDepartment(d);
    }

    @Override
    @Transactional
    public void deleteEmployee(Employee emp) {
        empRepo.delete(emp);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long ID) {
        //  Auto-generated method stub
        if (ID == null) {
            System.out.println("ID is null");
            return;
        }
        empRepo.deleteById(ID);
    }

    @Override
    @Transactional
    public Employee saveOrUpdate(Employee e) {
        return empRepo.save(e);
    }

}
