package yevhenii.lostfilmdemo.RSS;

public class RssTest {
    public static void main(String[] args) {
        RssFeedParser rssFeedParser = new RssFeedParser("https://www.lostfilm.tv/rss.xml");
        Feed feed = rssFeedParser.readFeed();
        System.out.println(feed);
        for (FeedMessage fm :
                feed.getMessages()) {
            System.out.println(fm);
        }
    }
}
