package yevhenii.lostfilmdemo.repository.impl;

import org.jooq.DSLContext;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TVSeriesRepositoryImplTest {

    @Autowired
    private DSLContext context;
    @Autowired
    private TVSeriesRepositoryImpl repository;
    private static final TVRecordConvertor recordConvertor = new TVRecordConvertor();


    private static final List<TvSeriesRecord> seriesList = new ArrayList<>();

    @ClassRule
    MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("lostfilm")
            .withUsername("root")
            .withPassword("root2021");

    @BeforeAll
    static void setFeed() {

        AtomicLong id = new AtomicLong(1);
        seriesList.add(recordConvertor.createRecord(
                TVSeries.builder().link("link1").season(1).name("name1").russianName("rname1").image("image1").episode(1).lastUpdate("1").build()));
        seriesList.add(recordConvertor.createRecord(
                TVSeries.builder().link("link2").season(2).name("name2").russianName("rname2").image("image2").episode(111).lastUpdate("2").build()));
        seriesList.add(recordConvertor.createRecord(
                TVSeries.builder().link("link3").season(3).name("name3").russianName("rname3").image("image3").episode(11).lastUpdate("3").build()));
        seriesList.forEach(value -> value.setId(id.getAndIncrement()));
    }

    @BeforeEach
    void fill() {
        seriesList.forEach(repository::create);
    }

    @AfterEach
    void cleanUp() {
        context.truncate("tv_series").execute();
    }

    @Test
    void create() {

        TvSeriesRecord record = (TvSeriesRecord) repository.read(seriesList.get(0).getLink()).get();
        assertThat(record).isEqualTo(seriesList.get(0));
    }

    @Test
    void readAll() {

        assertThat(repository.readAll().size()).isEqualTo(seriesList.size());
    }

    @Test
    void update() {

        repository.update(seriesList.get(0));
        assertThat(repository.readAll().size()).isEqualTo(seriesList.size());

        seriesList.get(1).setImage("image");
        repository.update(seriesList.get(1));
        assertThat(recordConvertor.convert((TvSeriesRecord)
                repository.read(seriesList.get(1).getLink())
                        .get()).getImage()
        )
                .isEqualTo("image");
    }

    @Test
    void delete() {

        repository.delete(seriesList.get(2).getLink());
        assertThat(repository.readAll().size()).isEqualTo(seriesList.size() - 1);
    }
}