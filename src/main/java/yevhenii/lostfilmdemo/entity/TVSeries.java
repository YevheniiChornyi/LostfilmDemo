package yevhenii.lostfilmdemo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TVSeries {
    @NonNull
    final private String name;
    @NonNull
    final private String russianName;
    private String image;
    private int season;
    private int episode;
    @NonNull
    private String link;
    private String lastUpdate;

}
