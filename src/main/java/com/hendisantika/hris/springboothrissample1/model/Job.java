package com.hendisantika.hris.springboothrissample1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 17:30
 */
@Entity
@Table(name = "JOBS")
@Data
public class Job {
    @Id
    @Column(name = "JOB_ID")
    private String jobId;

    @Column(name = "JOB_TITLE")
    private String JobTitle;

    @Column(name = "MIN_SALARY")
    private BigDecimal minSalary;

    @Column(name = "MAX_SALARY")
    private BigDecimal maxSalary;
}
