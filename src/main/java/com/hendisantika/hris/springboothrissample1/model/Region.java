package com.hendisantika.hris.springboothrissample1.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 17:27
 */
@Entity
@Table(name = "REGIONS")
@Data
public class Region {
    @Id
    @Column(name = "REGION_ID")
    private int regionId;

    @Column(name = "REGION_NAME")
    private String regionName;
}
