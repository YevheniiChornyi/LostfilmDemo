package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbEpisodesHolder {

    private String imDbId;
    private String title;
    private String fullTitle;
    private String type;
    private List<ImdbEpisode> episodes;

}
