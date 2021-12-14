package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

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

