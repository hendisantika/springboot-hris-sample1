package com.hendisantika.hris.springboothrissample1.repository;

import com.hendisantika.hris.springboothrissample1.model.Job;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2025-10-08
 * Time: 21:10
 */
public interface JobRepository extends CrudRepository<Job, String> {
}
