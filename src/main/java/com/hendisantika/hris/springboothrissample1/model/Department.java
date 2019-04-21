package com.hendisantika.hris.springboothrissample1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.*;
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
@Data
public class Department {
    @Id
    @GeneratedValue(generator = "DepartmentPKSequence")
    @SequenceGenerator(name = "DepartmentPKSequence", sequenceName = "DEPARTMENTS_SEQ", allocationSize = 5)
    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "LOCATION_ID")
    private Long locationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MANAGER_ID")
    @JsonBackReference
    private Employee manager;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Employee> deptEmployees = Lists.newArrayList();
}
