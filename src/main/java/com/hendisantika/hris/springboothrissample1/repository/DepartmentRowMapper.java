package com.hendisantika.hris.springboothrissample1.repository;

import com.hendisantika.hris.springboothrissample1.dto.DepartmentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 18:28
 */
public class DepartmentRowMapper implements RowMapper<DepartmentDTO> {

    @Override
    public DepartmentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        DepartmentDTO dep = new DepartmentDTO();
        dep.setDepartmentId(rs.getLong("DEPARTMENT_ID"));
        dep.setDeptName(rs.getString("DEPARTMENT_NAME"));
        dep.setLocationId(rs.getLong("LOCATION_ID"));
        dep.setManagerId(rs.getLong("MANAGER_ID"));
        return dep;
    }
}
