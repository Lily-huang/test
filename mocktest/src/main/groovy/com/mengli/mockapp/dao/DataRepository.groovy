package com.mengli.mockapp.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate


/**
 * Created by mlhuang on 8/10/16.
 */

class DataRepository {
    public static final String ISER_ID = 'no'
    public static final String NAME = 'name'
    public static final String AGE = 'age'

    @Autowired
    private JdbcTemplate jdbcTemplate

    void clean() {

    }

    void prepareStaffInfomation(String no, String property, int value) {
        jdbcTemplate.update("UPDATE staff set $property=$value WHERE staff_no=$no")
    }

    void prepareStaffInfomation(String no, String property, String value) {
        jdbcTemplate.update("UPDATE staff set $property=$value WHERE staff_no=$no")
    }
}
