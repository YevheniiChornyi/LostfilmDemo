package yevhenii.lostfilmdemo.services.impl;

import org.springframework.stereotype.Service;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.rss.FeedMessage;
import yevhenii.lostfilmdemo.services.ConvertService;

@Service
public class ConvertServiceImpl implements ConvertService {

    private String getName(StringBuilder str){
        return str.substring(str.indexOf("(")+1, str.indexOf(")"));
    }
    private String getRussianName(StringBuilder str){
        StringBuilder stringBuilder = str.delete(str.indexOf("("), str.indexOf(")")+1);
        stringBuilder.delete(stringBuilder.indexOf("("), stringBuilder.indexOf(")")+1);
        return stringBuilder.toString();
    }
    private int getSeason(StringBuilder str){
        return Character.getNumericValue(str.charAt((str.indexOf("season"))+7));
    }
    private int getEpisode(StringBuilder str){
        return Character.getNumericValue(str.charAt((str.indexOf("episode"))+8));
    }
    private String getImage(StringBuilder str){
        return str.substring(str.indexOf("static"), str.indexOf("jpg")+3);
    }

    @Override
    public TVSeries convert(FeedMessage feedMessage) {
        StringBuilder nameBuilder = new StringBuilder(feedMessage.getTitle());
        final String name = getName(nameBuilder);
        String russianName = getRussianName(nameBuilder).trim();
        nameBuilder = new StringBuilder(feedMessage.getLink());
        int season = getSeason(nameBuilder);
        int episode = getEpisode(nameBuilder);
        String link = nameBuilder.toString();
        String lastUpdate = feedMessage.getPubDate();
        nameBuilder = new StringBuilder(feedMessage.getDescription());
        String image = getImage(nameBuilder);

        return TVSeries.builder().episode(episode).image(image).lastUpdate(lastUpdate).link(link).name(name).season(season).russianName(russianName).build();
    }
}
