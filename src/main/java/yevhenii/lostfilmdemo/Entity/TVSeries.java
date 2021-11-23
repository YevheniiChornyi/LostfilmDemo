package yevhenii.lostfilmdemo.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class TVSeries {
    final private String name;
    final private String russianName;
    @Setter
    private String image;
    @Setter
    private int season;
    @Setter
    private int episode;
    @Setter
    private String link;

    @Override
    public String toString() {
        return "TVSeries{" +
                "name='" + name + '\'' +
                ", russianName='" + russianName + '\'' +
                ", image='" + image + '\'' +
                ", season=" + season +
                ", episode=" + episode +
                ", link='" + link + '\'' +
                '}';
    }
}
