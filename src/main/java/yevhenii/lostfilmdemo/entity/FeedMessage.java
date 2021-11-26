package yevhenii.lostfilmdemo.entity;

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
}
