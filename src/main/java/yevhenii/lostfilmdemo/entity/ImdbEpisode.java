package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbEpisode implements Serializable {
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
