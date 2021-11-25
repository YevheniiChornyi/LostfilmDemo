package yevhenii.lostfilmdemo.rss;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class FeedMessage {

    @NonNull
    String title;
    @NonNull
    String description;
    String pubDate;
    @NonNull
    String link;

    @Override
    public String toString() {
        return "FeedMessage{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", link='" + link + '\'' +
                '}' + '\n';
    }
}
