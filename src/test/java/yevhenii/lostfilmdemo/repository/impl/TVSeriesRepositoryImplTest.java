package yevhenii.lostfilmdemo.repository.impl;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;

//@ActiveProfiles("test")
@SpringBootTest
class TVSeriesRepositoryImplTest {

    @Autowired
    TVSeriesRepositoryImpl repository;

    @ClassRule
    public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest") //Raw use of parameterized class 'MySQLContainer'
            .withDatabaseName("integration-tests-db")
            .withUsername("root")
            .withPassword("root2021");

    @Test
    void create() {

    }

    @Test
    void read() {
    }

    @Test
    void readAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}