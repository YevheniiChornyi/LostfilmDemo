package yevhenii.lostfilmdemo.repository;

import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.entity.TVSeries;

@Transactional
public interface SchedulerRequestRepository {
//created
    public TVSeries create(TVSeries tvSeries);

    public TVSeries findByLink(String link);

    public TVSeries updateByLink(TVSeries updated);
}
