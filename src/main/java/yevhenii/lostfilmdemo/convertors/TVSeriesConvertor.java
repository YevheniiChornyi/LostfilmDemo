package yevhenii.lostfilmdemo.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TVSeriesConvertor implements Converter<FeedMessage, TVSeries> {

    //TODO instead of all this methods which are parsing String use regex expression with group for each value you wanna to extract
    private String getName(String regexp, String input){

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        StringBuilder result = new StringBuilder("");
        while (true){
            if (matcher.find()) {
                result.append(matcher.group())
                        .append(" ");
            } else {
                break;
            }
        }
        return result.toString();
    }

    private String toRegex(String regexp, String input) {

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find())
            return matcher.group(1);
        throw new IllegalArgumentException("not informative link");
    }

    @Override
    public TVSeries convert(FeedMessage feedMessage) {
        final String name = getName("([a-zA-Z.]{2,})", feedMessage.getTitle());
        String russianName = getName("([а-яА-Я.]+)", feedMessage.getTitle());
        int season = Integer.parseInt(toRegex("season_(\\d)", feedMessage.getLink()));
        int episode = Integer.parseInt(toRegex("episode_(\\d)", feedMessage.getLink()));
        String link = feedMessage.getLink();
        String lastUpdate = feedMessage.getPubDate();
        String image = toRegex("(static.+jpg)",feedMessage.getDescription());

        return TVSeries.builder()
                .episode(episode)
                .image(image)
                .lastUpdate(lastUpdate)
                .link(link)
                .name(name)
                .season(season)
                .russianName(russianName)
                .build();
    }
}
