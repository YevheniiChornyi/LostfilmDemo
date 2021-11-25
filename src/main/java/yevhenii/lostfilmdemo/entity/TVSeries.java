package yevhenii.lostfilmdemo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TVSeries {
    @NonNull
    final private String name;
    final private String russianName;
    private String image;
    private int season;
    private int episode;
    private String link;
    private String lastUpdate;

}
