package yevhenii.lostfilmdemo.imdb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;

@FeignClient(url = "https://imdb-api.com/en/API/", name = "imdb")
public interface ImdbHolderClient {
    @RequestMapping(method = RequestMethod.GET, value = "/SearchSeries/k_vm88jkhr/{Id}")
    int getId(@PathVariable String Id);

    @RequestMapping(method = RequestMethod.GET, value = "SeasonEpisodes/k_vm88jkhr/{seriesId}/{season}", produces = "application/json")
    List<TVSeries> getSeason(@PathVariable String seriesId, @PathVariable int season);
}
