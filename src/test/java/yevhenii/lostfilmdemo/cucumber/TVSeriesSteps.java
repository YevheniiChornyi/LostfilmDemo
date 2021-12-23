package yevhenii.lostfilmdemo.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.ClassRule;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import yevhenii.lostfilmdemo.repository.impl.TVSeriesRepositoryImpl;
import yevhenii.lostfilmdemo.services.impl.FeedServiceImpl;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TVSeriesSteps {

    @Value ("${lostfilm.rss.url}")
    private String url;
    @Autowired
    private TVSeriesRepositoryImpl repository;
    @Autowired
    private FeedServiceImpl feedService;
    @Autowired
    private Scheduler scheduler;

    @ClassRule
    private final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("lostfilm")
            .withUsername("root")
            .withPassword("root2021");

    @Before
    void init(){


    }
    @After
    void close(){

    }
    @Given("I go mock tv series sites")
    public void iGoMockTvSeriesSites()  {
        lostfilmStub();
    }

    @When("I go to find new series")
    public void iGoToLostfilmRss() throws SchedulerException, InterruptedException{
        scheduler.triggerJob(new JobKey("lostfilm_job", Scheduler.DEFAULT_GROUP));
        Thread.sleep(20000);
    }

    @Then("I should get TVSeries")
    public void iShouldGetTVSeries() {
        verify((getRequestedFor(urlEqualTo("/www.lostfilm.tv/rss.xml"))));
    }

    @And("put it into db")
    public void putItIntoDb() {
        assertThat(repository.readAll().size()).isGreaterThanOrEqualTo(15);
    }

    @And("send message, that we should find info from imdb and also put it to db")
    public void sendMessageThatWeShouldFindInfoFromImdb() {
        //TODO stub IMDB
    }
    private void lostfilmStub(){
        configureFor("localhost", 8089);
        stubFor(get(urlMatching("rss.xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("RSS.xml")));
    }


}
