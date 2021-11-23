package yevhenii.lostfilmdemo.RSS;

import yevhenii.lostfilmdemo.Services.FeedService;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RssFeedParser implements FeedService {
    private final URL url;

    public RssFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    //    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String LAST_BUILD_DATE = "lastBuildDate";
    static final String LINK = "link";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";

    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values initial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String pubDate = "";
            String lastBuildDate = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, description, link, lastBuildDate, language);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(eventReader, event);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(eventReader, event);
                            break;
                        case LINK:
                            link = getCharacterData(eventReader, event);
                            break;
                        case LAST_BUILD_DATE:
                            lastBuildDate = getCharacterData(eventReader, event);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(eventReader, event);
                            break;
                        case PUB_DATE:
                            pubDate = getCharacterData(eventReader, event);
                            break;
                        default:

                    }

                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        assert feed != null;
                        feed.add(new FeedMessage(title, description, pubDate, link));
                        event = eventReader.nextEvent();
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private String getCharacterData(XMLEventReader eventReader, XMLEvent event)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
