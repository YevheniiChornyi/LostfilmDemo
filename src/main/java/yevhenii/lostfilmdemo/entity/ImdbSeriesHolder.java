package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbSeriesHolder {

    private String searchType;
    private String expression;
    private String errorMessage;
    private String year;
    private List<ImdbSeries> results;

}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ImdbSeries implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("resultType")
    private String resultType;
    @JsonProperty("image")
    private String image;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
}