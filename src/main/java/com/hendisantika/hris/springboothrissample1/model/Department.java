package com.hendisantika.hris.springboothrissample1.model;

import com.google.common.collect.Lists;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 17:33
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department {

    private String DepartmentName;

    private Long Location_ID;

    private Long departmentId;

    private List<Employee> deptEmployees = Lists.newArrayList();
}
