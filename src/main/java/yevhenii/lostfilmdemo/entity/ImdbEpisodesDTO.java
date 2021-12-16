package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbEpisodesDTO {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Released")
    private String released;

    @JsonProperty("Season")
    private int seasonNumber;

    @JsonProperty("Episode")
    private int episodeNumber;

    @JsonProperty("Runtime")
    private String runtime;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Writer")
    private String writer;

    @JsonProperty("Actors")
    private String actors;

    @JsonProperty("Plot")
    private String plot;

    private String imdbRating;

    @JsonProperty("Metascore")
    private String metaScore;

    private String imdbVotes;

    private String seriesID;

    private String imdbID;
}
