package yevhenii.lostfilmdemo.repository;

import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.entity.TVSeries;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRequestRepository {

    public Optional<List<TVSeries>> findALl();

    public TVSeries deleteByLink(String link);

    public TVSeries findByName(String name);

    public TVSeries findByRussianName(String russianName);

}
