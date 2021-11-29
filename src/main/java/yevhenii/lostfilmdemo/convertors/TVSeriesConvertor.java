package yevhenii.lostfilmdemo.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TVSeriesConvertor implements Converter<FeedMessage, TVSeries> {

    private final static Pattern TITLE_PATTERN = Pattern.compile("(.+)\\((.+)\\)(.+)(\\(S0.+)");
    private final static Pattern LINK_PATTERN = Pattern.compile("season_(\\d+)/episode_(\\d+)");
    private final static Pattern DESCRIPTION_PATTERN = Pattern.compile("(static.+jpg)");

    @Override
    public TVSeries convert(FeedMessage feedMessage) {
        Matcher matcher = TITLE_PATTERN.matcher(feedMessage.getTitle());
        if (!matcher.find()) throw new IllegalArgumentException("Wrong title format");
        final String name = matcher.group(2);
        String russianName = matcher.group(1) + matcher.group(3);
        matcher = LINK_PATTERN.matcher(feedMessage.getLink());
        if (!matcher.find()) throw new IllegalArgumentException("Wrong link format");
        int season = Integer.parseInt(matcher.group(1));
        int episode = Integer.parseInt(matcher.group(2));
        matcher = DESCRIPTION_PATTERN.matcher(feedMessage.getDescription());
        if (!matcher.find()) throw new IllegalArgumentException("Wrong description format");
        String image = matcher.group();

        return TVSeries.builder()
                .episode(episode)
                .image(image)
                .lastUpdate(feedMessage.getPubDate())
                .link(feedMessage.getLink())
                .name(name)
                .season(season)
                .russianName(russianName)
                .build();
    }
}
