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
 * Time: 17:25
 */
@Entity
@Table(name = "COUNTRIES")
@Data
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    private String countryId;

    @Column(name = "REGION_ID")
    private Long regionId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

}
