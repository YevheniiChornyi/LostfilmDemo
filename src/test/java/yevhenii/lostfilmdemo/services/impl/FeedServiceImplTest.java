package yevhenii.lostfilmdemo.services.impl;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import yevhenii.lostfilmdemo.LostfilmDemoApplication;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.services.FeedService;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@WireMockTest
@AutoConfigureWireMock
@SpringBootTest(classes = LostfilmDemoApplication.class)
//@ActiveProfiles("test")
class FeedServiceImplTest {

    @Autowired
    private FeedService feedService;
    public List<FeedMessage> messageList;

    @Test
    void readFeedTest() {

        String url = "http://localhost:8080/www.lostfilm.tv/rss.xml";
        String stubUrl = "/www.lostfilm.tv/rss.xml";

        stubFor(get(urlEqualTo(stubUrl))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("RSS.xml")));

        messageList = feedService.readFeed(url);

        verify((getRequestedFor(urlEqualTo(stubUrl))));

        assertThat(messageList.size()).isEqualTo(15);
    }
}