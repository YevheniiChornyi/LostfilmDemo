package yevhenii.lostfilmdemo.repository.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
@SpringBootTest
class TVSeriesRepositoryImplTest {

    @Autowired
    TVSeriesRepositoryImpl repository;

    @BeforeAll
    static void setUp(){
        try (MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
                .withDatabaseName("lostfilm")
                .withUsername("root")
                .withPassword("root2021")){
            mySQLContainer.start();
        }

    }
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