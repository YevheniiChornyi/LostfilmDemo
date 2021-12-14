package yevhenii.lostfilmdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImdbSeries implements Serializable {

    @Getter
    private String id;
    private String resultType;
    private String image;
    private String title;
    private String description;
}
