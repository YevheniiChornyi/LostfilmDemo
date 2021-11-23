package yevhenii.lostfilmdemo.RSS;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class Feed {
    @Override
    public String toString() {
        return "Feed{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    private final String title;
    private final String description;
    private final String link;
    private final String lastBuildDate;
    private final String language;

    private final List<FeedMessage> messages = new ArrayList<>();

    public void add(FeedMessage feedMessage) {
        messages.add(feedMessage);
    }
}
