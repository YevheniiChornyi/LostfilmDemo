package yevhenii.lostfilmdemo.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesDTO;

@Profile("!test")
@FeignClient(url = "https://www.omdbapi.com/", name = "omdb")
public interface ImdbClient {

    @GetMapping()
    ImdbEpisodesDTO getEpisode(@RequestParam("apikey") String apiKey, @RequestParam("t") String title,
                               @RequestParam("Season") int season, @RequestParam("Episode") int episode);
}

