package com.astralbrands.sage.x3.processTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import com.astralbrands.sage.x3.dao.BitBootRepository;
import com.astralbrands.sage.x3.dao.X3Repository;
import com.astralbrands.sage.x3.process.BrandListProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class BrandListProcessTest {




    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    public void setUpDatabase() {
        jdbc.execute("");
    }
    @Test
    void getCsvFileDataTest() {
        Map <String, Object> Tmap = new HashMap<>();
        BrandListProcess p = new BrandListProcess();


    }
}
