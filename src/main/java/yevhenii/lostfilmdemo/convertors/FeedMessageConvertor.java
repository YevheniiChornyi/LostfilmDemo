package yevhenii.lostfilmdemo.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import yevhenii.lostfilmdemo.entity.FeedMessage;

@Component
public class FeedMessageConvertor implements Converter<org.w3c.dom.Element, FeedMessage> {
    @Override
    public FeedMessage convert(Element element) {
        String title = element.getElementsByTagName("title").item(0).getTextContent();//should I split these methods too?
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();
        String link = element.getElementsByTagName("link").item(0).getTextContent();

        return FeedMessage.builder()
                .description(description)
                .link(link)
                .pubDate(pubDate)
                .title(title)
                .build();
    }
}
