package com.hendisantika.hris.springboothrissample1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
