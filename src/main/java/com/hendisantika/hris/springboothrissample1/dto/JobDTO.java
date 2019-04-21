package com.hendisantika.hris.springboothrissample1.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 18:26
 */
@Data
public class JobDTO implements Comparable<JobDTO> {
    private String jobId;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;


    @Override
    public int compareTo(JobDTO o) {
        return this.jobTitle.compareTo(o.jobTitle);
    }
}
