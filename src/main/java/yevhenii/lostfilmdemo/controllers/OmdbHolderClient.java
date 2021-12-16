package yevhenii.lostfilmdemo.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import yevhenii.lostfilmdemo.entity.ImdbEpisodesDTO;

@FeignClient(url = "https://www.omdbapi.com", name = "omdb")
public interface OmdbHolderClient {

    @GetMapping("/?apikey={apiKey}&t={title}&Season={season}&Episode={episode}")
    ImdbEpisodesDTO getEpisode(@PathVariable String apiKey, @PathVariable String title, @PathVariable int season, @PathVariable int episode);
}

