package com.hendisantika.hris.springboothrissample1.dto;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 18:22
 */
public class DepartmentDTO implements Comparable<DepartmentDTO> {
    private String deptName;
    private Long managerId;
    private Long locationId;
    private Long departmentId;

    @Override
    public int compareTo(DepartmentDTO o) {
        return this.deptName.compareTo(o.deptName);
    }
}
