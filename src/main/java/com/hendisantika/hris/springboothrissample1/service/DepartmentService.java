package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.dto.DepartmentDTO;
import com.hendisantika.hris.springboothrissample1.model.Department;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:34
 */
public interface DepartmentService {
    List<DepartmentDTO> getDepartments();

    Department getOne(Long ID);
}
