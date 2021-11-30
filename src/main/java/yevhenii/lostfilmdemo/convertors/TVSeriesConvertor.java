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

        return TVSeries.builder()
                .russianName(getRussianName(feedMessage.getTitle()))
                .name(getName(feedMessage.getTitle()))
                .image(getImage(feedMessage.getDescription()))
                .lastUpdate(feedMessage.getPubDate())
                .link(feedMessage.getLink())
                .season(getSeason(feedMessage.getLink()))
                .episode(getEpisode(feedMessage.getLink()))
                .build();
    }

    private String getName(String input) {
        Matcher matcher = TITLE_PATTERN.matcher(input);
        if (!matcher.find()) throw new IllegalArgumentException("Wrong title format");
        return matcher.group(2);
    }

    private String getRussianName(String input) {
        Matcher matcher = TITLE_PATTERN.matcher(input);
        if (!matcher.find()) throw new IllegalArgumentException("Wrong title format");
        return matcher.group(1) + matcher.group(3);
    }

    private int getSeason(String input) {
        Matcher LinkMatcher = LINK_PATTERN.matcher(input);
        if (!LinkMatcher.find()) throw new IllegalArgumentException("Wrong link format");
        return Integer.parseInt(LinkMatcher.group(1));
    }

    private int getEpisode(String input) {
        Matcher LinkMatcher = LINK_PATTERN.matcher(input);
        if (!LinkMatcher.find()) throw new IllegalArgumentException("Wrong link format");
        return Integer.parseInt(LinkMatcher.group(2));
    }

    private String getImage(String input) {
        Matcher descriptionMatcher = DESCRIPTION_PATTERN.matcher(input);
        if (!descriptionMatcher.find()) throw new IllegalArgumentException("Wrong description format");
        return descriptionMatcher.group();
    }

}
