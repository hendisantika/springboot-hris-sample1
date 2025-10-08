package com.hendisantika.hris.springboothrissample1.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 18:23
 */
@Data
public class EmployeeDTO implements Comparable<EmployeeDTO> {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private String jobTitle;
    private BigDecimal salary;
    private BigDecimal commissionPercent;
    private Long managerId;
    private Long departmentId;
    private String departmentName;
    private String updateLink;
    private String deleteLink;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public int compareTo(EmployeeDTO other) {
        return this.getFullName().compareTo(other.getFullName());
    }
}
