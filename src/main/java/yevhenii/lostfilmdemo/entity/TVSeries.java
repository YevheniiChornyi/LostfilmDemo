package yevhenii.lostfilmdemo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TVSeries {
    @NonNull
    private final String name;
    @NonNull
    private final String russianName;
    private String image;
    private final int season;
    private final int episode;
    @NonNull
    private final String link;
    private String lastUpdate;
    //IMDB
    private ImdbEpisode imdbEpisode;
}
