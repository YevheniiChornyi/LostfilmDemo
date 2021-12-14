package yevhenii.lostfilmdemo.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesHolder;
import yevhenii.lostfilmdemo.entity.ImdbSeriesHolder;

@FeignClient(url = "https://imdb-api.com/en/API/", name = "imdb")
public interface ImdbHolderClient {

    @GetMapping("/SearchSeries/k_vm88jkhr/{Id}")
    ImdbSeriesHolder getSeries(@PathVariable String Id);

    @GetMapping( "/SeasonEpisodes/k_vm88jkhr/{seriesId}/{season}")
    ImdbEpisodesHolder getSeason(@PathVariable String seriesId, @PathVariable int season);

}

