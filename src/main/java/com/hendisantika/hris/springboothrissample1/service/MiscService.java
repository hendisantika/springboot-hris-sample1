package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.dto.JobDTO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:37
 */
public interface MiscService {
    List<JobDTO> getJobs();

    JobDTO getJobDTOByID(String ID);

}
