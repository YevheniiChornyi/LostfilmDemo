package yevhenii.lostfilmdemo.repository.impl;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.jooq.DSLContext;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import yevhenii.lostfilmdemo.LostfilmDemoApplication;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest
@ActiveProfiles("test")
@SpringBootTest(classes = LostfilmDemoApplication.class)
@AutoConfigureWireMock
class TVSeriesRepositoryImplTest {

    @Autowired
    private TVSeriesRepositoryImpl repository;
    @Autowired
    private TVRecordConvertor recordConvertor;
    @Autowired
    private DSLContext context;

    private static final List<TVSeries> seriesList = new ArrayList<>();

    @ClassRule
    MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("lostfilm")
            .withUsername("root")
            .withPassword("root2021");

    @BeforeAll
    static void setFeed() {

        seriesList.add(TVSeries.builder().link("link1").season(1).name("name1").russianName("rname1").image("image1").episode(1).lastUpdate("1").build());
        seriesList.add(TVSeries.builder().link("link2").season(2).name("name2").russianName("rname2").image("image2").episode(111).lastUpdate("2").build());
        seriesList.add(TVSeries.builder().link("link3").season(3).name("name3").russianName("rname3").image("image3").episode(11).lastUpdate("3").build());
        seriesList.add(TVSeries.builder().link("link4").season(4).name("name4").russianName("rname4").image("image4").episode(21).lastUpdate("4").build());
        seriesList.add(TVSeries.builder().link("link5").season(5).name("name5").russianName("rname5").image("image5").episode(13).lastUpdate("5").build());
        seriesList.add(TVSeries.builder().link("link6").season(6).name("name6").russianName("rname6").image("image6").episode(12).lastUpdate("6").build());
        seriesList.add(TVSeries.builder().link("link7").season(7).name("name7").russianName("rname7").image("image7").episode(31).lastUpdate("7").build());
        seriesList.add(TVSeries.builder().link("link8").season(8).name("name8").russianName("rname8").image("image8").episode(12).lastUpdate("8").build());
        seriesList.add(TVSeries.builder().link("link9").season(9).name("name9").russianName("rname9").image("image9").episode(188).lastUpdate("9").build());

    }

    @AfterEach
    void cleanUp() {
        context.truncate("tv_series").execute();
    }

    @Test
    void create() {

        repository.create(recordConvertor.createRecord(seriesList.get(0)));
        TVSeries tvSeries = recordConvertor.convert((TvSeriesRecord) repository.read(seriesList.get(0).getLink()).get());
        tvSeries.setImdbEpisode(null);

        assertThat(tvSeries).isEqualTo(seriesList.get(0));
    }

    @Test
    void readAll() {
        seriesList.forEach(a -> repository.create(recordConvertor.createRecord(a)));
        assertThat(repository.readAll().size()).isEqualTo(seriesList.size());
    }

    @Test
    void update() {
        seriesList.stream()
                .peek(a -> repository.create(recordConvertor.createRecord(a)))
                .forEach(a -> repository.update(recordConvertor.createRecord(a)));
        assertThat(repository.readAll().size()).isEqualTo(seriesList.size());

        seriesList.get(6).setImage("image");
        repository.update(recordConvertor.createRecord(seriesList.get(6)));
        assertThat(recordConvertor.convert((TvSeriesRecord)
                repository.read(seriesList.get(6).getLink())
                        .get()).getImage()
        )
                .isEqualTo("image");
    }

    @Test
    void delete() {

        seriesList.stream()
                .peek(a -> repository.create(recordConvertor.createRecord(a)))
                .skip(5)
                .forEach(a -> repository.delete(a.getLink()));
        assertThat(repository.readAll().size()).isEqualTo(seriesList.size() - (seriesList.size() - 5));
    }
}