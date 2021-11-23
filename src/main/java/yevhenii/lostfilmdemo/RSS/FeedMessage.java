package yevhenii.lostfilmdemo.RSS;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


//immutable?
@RequiredArgsConstructor
@Getter
public class FeedMessage {
    @Override
    public String toString() {
        return "FeedMessage{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    private final String title;
    private final String description;
    private final String pubDate;
    private final String link;
}
