package yevhenii.lostfilmdemo.rss;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class Feed {

    String title;
    String description;
    String link;
    String lastBuildDate;
    String language;
}
