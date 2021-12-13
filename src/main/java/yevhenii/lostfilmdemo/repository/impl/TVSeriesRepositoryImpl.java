package yevhenii.lostfilmdemo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

import java.util.List;
import java.util.Optional;

import static yevhenii.lostfilmdemo.jooq.generated.tables.TvSeries.TV_SERIES;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class TVSeriesRepositoryImpl implements yevhenii.lostfilmdemo.repository.TVSeriesRepository {

    private final DSLContext jooq;

    @Override
    public void create(Record record) {

        jooq.insertInto(TV_SERIES)
                .set(record)
                .execute();

    }

    @Override
    public Optional<Record> read(String link) {

        TvSeriesRecord record = jooq.selectFrom(TV_SERIES)
                .where(TV_SERIES.LINK.equal(link))
                .fetchOne();
        return Optional.ofNullable(record);

    }

    @Override
    public List<TvSeriesRecord> readAll() {

        return
                jooq.selectFrom(TV_SERIES)
                        .fetchInto(TvSeriesRecord.class);
    }

    @Override
    public void update(TvSeriesRecord record) {

        jooq.update(TV_SERIES)
//                .set(TV_SERIES.LASTUPDATE, record.getLastupdate())
//                .set(TV_SERIES.IMAGE, record.getImage())
                .set(record)
                .where(TV_SERIES.LINK.equal(record.getLink()))
                .execute();
    }

    @Override
    public void delete(String link) {

        jooq.delete(TV_SERIES)
                .where(TV_SERIES.LINK.equal(link))
                .execute();

    }


}
