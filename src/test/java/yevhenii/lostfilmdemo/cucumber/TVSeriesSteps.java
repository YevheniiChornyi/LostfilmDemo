package yevhenii.lostfilmdemo.cucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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


    private final MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"))
            .withDatabaseName("lostfilm")
            .withUsername("root")
            .withPassword("root2021");

    @Before
    void init(){
        mySQLContainer.start();

    }

    @When("I go to lostfilm.rss")
    public void iGoToLostfilmRss() throws InterruptedException, SchedulerException {
        scheduler.triggerJob(new JobKey("lostfilm_job", Scheduler.DEFAULT_GROUP));
        Thread.sleep(20000);
    }

    @Then("I should get TVSeries")
    public void iShouldGetTVSeries() {
        assertThat(feedService.readFeed(url)).isNotNull();
        //should I wiremock it?
    }

    @And("put it into db")
    public void putItIntoDb() {
        assertThat(repository.readAll().size()).isGreaterThanOrEqualTo(15);
    }

    @And("send message, that we should find info from imdb and also put it to db")
    public void sendMessageThatWeShouldFindInfoFromImdb() {
        repository.readAll().forEach(a->assertThat(a.getTitle()).isNotNull());
        mySQLContainer.stop();
    }
}
