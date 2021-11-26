package yevhenii.lostfilmdemo.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import yevhenii.lostfilmdemo.entity.FeedMessage;
import yevhenii.lostfilmdemo.services.FeedService;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedServiceImpl implements FeedService {

    private final Converter<Element, FeedMessage> converter;
    @Override
    public List<FeedMessage> readFeed(String url) {

        try {
            List<FeedMessage> messages = new ArrayList<>();
            NodeList nodeList = readDocument(url).getElementsByTagName("item");
            IntStream.range(0, nodeList.getLength())
                    .mapToObj(nodeList::item)
                    .filter((a)-> a.getNodeType() == Node.ELEMENT_NODE)
                    .forEach((a)-> messages.add(converter.convert((Element) a)));
            return messages;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new IllegalArgumentException("XML file access error");
        }
    }
    private Document readDocument(String url) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        Document document = documentBuilderFactory.newDocumentBuilder().parse(url);
        document.getDocumentElement().normalize();
        return document;
    }

}
