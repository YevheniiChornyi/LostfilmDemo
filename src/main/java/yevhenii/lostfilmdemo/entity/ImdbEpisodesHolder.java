package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbEpisodesHolder {

    private String imDbId;
    private String title;
    private String fullTitle;
    private String type;
    private List<ImdbEpisodes> episodes;

}
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ImdbEpisodes implements Serializable {
    private String id;
    private int seasonNumber;
    private int episodeNumber;
    private String title;
    private String image;
    private int year;
    private String released;
    private String plot;
    private float imDbRating;
    private int imDbRatingCount;

}