package com.lwt.dataexport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class DataexportApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    public void test(){
        System.out.println(dataSource.getClass());
    }

}
