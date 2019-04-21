package com.hendisantika.hris.springboothrissample1.repository;

import com.hendisantika.hris.springboothrissample1.dto.JobDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:31
 */
public class JobRowMapper implements RowMapper<JobDTO> {

    @Override
    public JobDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        JobDTO job = new JobDTO();
        job.setJobId(rs.getString("JOB_ID"));
        job.setJobTitle(rs.getString("JOB_TITLE"));
        job.setMaxSalary(rs.getBigDecimal("MAX_SALARY"));
        job.setMinSalary(rs.getBigDecimal("MIN_SALARY"));
        return job;
    }

}
