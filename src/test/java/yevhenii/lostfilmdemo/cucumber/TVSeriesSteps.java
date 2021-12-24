package yevhenii.lostfilmdemo.cucumber;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.ClassRule;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.repository.impl.TVSeriesRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TVSeriesSteps {

    @Autowired
    private TVSeriesRepositoryImpl repository;
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private TVRecordConvertor convertor;

    @ClassRule
    private final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("lostfilm")
            .withUsername("root")
            .withPassword("root2021");

    @Given("I go mock tv series sites")
    public void iGoMockTvSeriesSites() {
        wiremockStub();
    }

    @When("I go to find new series")
    public void iGoToLostfilmRss() throws SchedulerException, InterruptedException {
        scheduler.triggerJob(new JobKey("lostfilm_job", Scheduler.DEFAULT_GROUP));
        Thread.sleep(20000);
    }

    @Then("I should get TVSeries")
    public void iShouldGetTVSeries() {
        verify((getRequestedFor(urlEqualTo("/www.lostfilm.tv/rss.xml"))));
    }


    @And("put tvSeries into db:")
    public void putTvSeriesIntoDb(List<TVSeries> seriesList) {
        AtomicInteger count = new AtomicInteger();
        repository.readAll().stream()
                .map(convertor::convert)
                .peek(a -> a.setImdbEpisode(null))
                .forEach(a -> assertThat(a)
                        .isEqualTo(seriesList.get(count.getAndIncrement())));

    }

    @And("send message, that we should find info from imdb and also put updated tvSeries to db")
    public void sendMessageThatWeShouldFindInfoFromImdbAndAlsoPutUpdatedTvSeriesToDb() {
        verify(getRequestedFor(urlMatching("/www\\.omdbapi\\.com.+")));
        repository.readAll().stream().map(convertor::convert).forEach(a -> assertThat(a.getImdbEpisode()).isNotNull());
    }

    @DataTableType
    public TVSeries tvSeriesEntry(Map<String, String> entry) {
        return TVSeries.builder()
                .episode(Integer.parseInt(entry.get("episode")))
                .season(Integer.parseInt(entry.get(("season"))))
                .russianName(entry.get("russianName"))
                .name(entry.get("name"))
                .lastUpdate(entry.get("lastUpdate"))
                .image(entry.get("image"))
                .link(entry.get("link"))
                .build();

    }

    private void wiremockStub() {
        configureFor("localhost", 8089);
        stubFor(get(urlEqualTo("/www.lostfilm.tv/rss.xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("RSSs.xml")));
        stubFor(get(urlMatching("/www\\.omdbapi\\.com.+"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type","application/json")
                        .withBodyFile("imdb.json")));
    }
}
