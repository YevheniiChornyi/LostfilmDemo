package yevhenii.lostfilmdemo.repository.impl;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import yevhenii.lostfilmdemo.LostfilmDemoApplication;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.convertors.TVSeriesConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.services.FeedService;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest
@ActiveProfiles("test")
@SpringBootTest(classes = LostfilmDemoApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureWireMock
class TVSeriesRepositoryImplTest {

    @Autowired
    private TVSeriesRepositoryImpl repository;
    @Autowired
    private TVSeriesConvertor convertor;
    @Autowired
    private FeedService feedService;
    @Autowired
    TVRecordConvertor recordConvertor;

    private List<TVSeries> seriesList;
    String url = "http://localhost:8080/www.lostfilm.tv/rss.xml";

    @ClassRule
    MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("lostfilm")
            .withUsername("root")
            .withPassword("root2021");

    @BeforeAll
    static void setUp() {

        String stubUrl = "/www.lostfilm.tv/rss.xml";

        stubFor(get(urlEqualTo(stubUrl))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("RSS.xml")));

    }

    @BeforeEach
    void setFeed() {

        seriesList = feedService.readFeed(url)
                .stream()
                .map(convertor::convert)
                .collect(Collectors.toList());
    }

    @Test
    @Order(1)
    void create() {

        repository.create(recordConvertor.createRecord(seriesList.get(0)));
        TVSeries tvSeries = recordConvertor.convert((TvSeriesRecord) repository.read(seriesList.get(0).getLink()).get());
        if (tvSeries != null) {
            tvSeries.setImdbEpisode(null);
        }
        assertThat(tvSeries).isEqualTo(seriesList.get(0));
    }

    @Test
    @Order(2)
    void readAll() {
        seriesList.stream().skip(1).forEach(a -> repository.create(recordConvertor.createRecord(a)));
        assertThat(repository.readAll().size()).isEqualTo(15);
    }

    @Test
    @Order(3)
    void update() {
        seriesList.forEach(a -> repository.update(recordConvertor.createRecord(a)));
        assertThat(repository.readAll().size()).isEqualTo(15);

        seriesList.get(6).setImage("image");
        repository.update(recordConvertor.createRecord(seriesList.get(6)));
        assertThat(recordConvertor.convert((TvSeriesRecord)
                repository.read(seriesList.get(6).getLink())
                        .get()).getImage()
        )
                .isEqualTo("image");
    }

    @Test
    @Order(4)
    void delete() {
        seriesList.stream().skip(5).forEach(a -> repository.delete(a.getLink()));
        assertThat(repository.readAll().size()).isEqualTo(5);
    }
}