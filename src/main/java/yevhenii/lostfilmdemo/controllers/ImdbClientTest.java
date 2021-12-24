package yevhenii.lostfilmdemo.controllers;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile("test")
@FeignClient(url = "http://localhost:8089/www.omdbapi.com/", name = "omdb")
public interface ImdbClientTest extends ImdbClient {
}

