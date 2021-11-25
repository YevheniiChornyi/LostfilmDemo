package yevhenii.lostfilmdemo.rss;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import yevhenii.lostfilmdemo.services.FeedService;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentParser implements FeedService {
    private final List<FeedMessage> messages = new ArrayList<>();

    Document readDocument(String url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        Document document = documentBuilderFactory.newDocumentBuilder().parse(url);
        document.getDocumentElement().normalize();
        return document;
    }

    void splitNodes(Document document){

        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                messages.add(getFeedMessage((Element) node));
            }
        }
    }

    FeedMessage getFeedMessage(Element element){

        String title = element.getElementsByTagName("title").item(0).getTextContent();
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        String pubDate = element.getElementsByTagName("pubDate").item(0).getTextContent();
        String link = element.getElementsByTagName("link").item(0).getTextContent();
        return FeedMessage.builder().description(description).link(link).pubDate(pubDate).title(title).build();
    }

    @Override
    public List<FeedMessage> readFeed(String url) throws ParserConfigurationException, IOException, SAXException {
        splitNodes(readDocument(url));
        return messages;
    }
}
