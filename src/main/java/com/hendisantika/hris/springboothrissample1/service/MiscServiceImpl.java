package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.dto.JobDTO;
import com.hendisantika.hris.springboothrissample1.repository.MiscDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:38
 */
@Service
public class MiscServiceImpl implements MiscService {

    @Autowired
    private MiscDao m;

    @Override
    @Transactional(readOnly = true)
    public List<JobDTO> getJobs() {
        return m.getAllJobs();
    }

    @Override
    @Transactional(readOnly = true)
    public JobDTO getJobDTOByID(String ID) {
        return m.getJobByID(ID);
    }


}