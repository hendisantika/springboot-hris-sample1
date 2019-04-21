package com.hendisantika.hris.springboothrissample1.model;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-21
 * Time: 17:28
 */
@Entity
@Table(name = "LOCATION")
public class Location {
    @Id
    @GeneratedValue(generator = "LocationSeq")
    @SequenceGenerator(name = "LocationSeq", sequenceName = "LOCATIONS_SEQ", allocationSize = 5)
    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "STREET_ADDRESS")
    private String streetAddress;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE_PROVINCE")
    private String state;

    @Column(name = "COUNTRY_ID")
    private String countryId;
}
