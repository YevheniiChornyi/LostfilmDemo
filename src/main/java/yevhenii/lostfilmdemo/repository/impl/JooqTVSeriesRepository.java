package yevhenii.lostfilmdemo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.repository.SchedulerRequestRepository;
import yevhenii.lostfilmdemo.repository.UserRequestRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static yevhenii.lostfilmdemo.jooq.generated.tables.TvSeries.TV_SERIES;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JooqTVSeriesRepository implements SchedulerRequestRepository, UserRequestRepository {
    private final DSLContext jooq;
//created
    @Override
    public TVSeries create(TVSeries tvSeries) {

        TvSeriesRecord record = jooq.insertInto(TV_SERIES)
                .set(createRecord(tvSeries))
                .returning()
                .fetchOne();
        assert record != null;
        return convertQueryResultToModelObject(record);
    }

    @Override
    @Transactional(readOnly = true)
    public TVSeries findByLink(String link) {

        TvSeriesRecord record = jooq.selectFrom(TV_SERIES)
                .where(TV_SERIES.LINK.equal(link))
                .fetchOne();
        if (record == null) throw new IllegalArgumentException("TV series not found by link " + link);
        return convertQueryResultToModelObject(record);

    }

    @Override
    @Transactional(readOnly = true)
    public TVSeries findByName(String name) {

        TvSeriesRecord record = jooq.selectFrom(TV_SERIES)
                .where(TV_SERIES.NAME.equal(name))
                .fetchOne();
        if (record == null) throw new IllegalArgumentException("TV series not found by name " + name);
        return convertQueryResultToModelObject(record);
    }

    @Override
    @Transactional(readOnly = true)
    public TVSeries findByRussianName(String russianName) {

        TvSeriesRecord record = jooq.selectFrom(TV_SERIES)
                .where(TV_SERIES.RUSSIAN_NAME.equal(russianName))
                .fetchOne();
        if (record == null) throw new IllegalArgumentException("TV series not found by name " + russianName);
        return convertQueryResultToModelObject(record);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TVSeries>> findALl() {

        return Optional.of(Stream.of(
                        jooq.selectFrom(TV_SERIES)
                                .fetchInto(TvSeriesRecord.class))
                .map(a -> convertQueryResultToModelObject((TvSeriesRecord) a))
                .collect(Collectors.toList()));
    }

    @Override
    public TVSeries deleteByLink(String link) {

        TVSeries deleted = findByLink(link);
        jooq.delete(TV_SERIES)
                .where(TV_SERIES.LINK.equal(link))
                .execute();

        return deleted;
    }

    @Override
    public TVSeries updateByLink(TVSeries updated) {

        jooq.update(TV_SERIES)
                .set(TV_SERIES.EPISODE, updated.getEpisode())
                .set(TV_SERIES.SEASON, updated.getSeason())
                .set(TV_SERIES.LASTUPDATE, updated.getLastUpdate())
                .set(TV_SERIES.LINK, updated.getLink())
                .set(TV_SERIES.IMAGE, updated.getImage())
                .where(TV_SERIES.LINK.equal(updated.getLink()))
                .execute();
        return findByLink(updated.getLink());
    }

    private TvSeriesRecord createRecord(TVSeries tvSeries) {

        TvSeriesRecord record = new TvSeriesRecord();
        record.setImage(tvSeries.getImage());
        record.setEpisode(tvSeries.getEpisode());
        record.setSeason(tvSeries.getSeason());
        record.setName(tvSeries.getName());
        record.setRussianName(tvSeries.getRussianName());
        record.setLink(tvSeries.getLink());
        record.setLastupdate(tvSeries.getLastUpdate());

        return record;
    }

    private TVSeries convertQueryResultToModelObject(TvSeriesRecord queryResult) {

        return
                TVSeries.builder()
                        .name(queryResult.getName())
                        .russianName(queryResult.getRussianName())
                        .link(queryResult.getLink())
                        .image(queryResult.getImage())
                        .episode(queryResult.getEpisode())
                        .season(queryResult.getSeason())
                        .build()
                ;
    }
}
