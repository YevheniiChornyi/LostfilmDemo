package yevhenii.lostfilmdemo.rss;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Feed {

    String title;
    String description;
    String link;
    String lastBuildDate;
    String language;
}
