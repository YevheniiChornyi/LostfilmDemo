package yevhenii.lostfilmdemo.repository.impl;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.repository.JooqRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static yevhenii.lostfilmdemo.jooq.generated.tables.TvSeries.TV_SERIES;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class JooqTVSeriesRepository implements JooqRepository<String> {

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
    public List<Record> readAll() {

        return Stream.of(
                        jooq.selectFrom(TV_SERIES)
                                .fetchInto(TvSeriesRecord.class))
                .map(a -> (TvSeriesRecord) a)
                .collect(Collectors.toList());
    }

    @Override
    public void update(Record record, String key) {

        jooq.update(TV_SERIES)
                .set(record)
                .where(TV_SERIES.LINK.equal(key))
                .execute();
    }

    @Override
    public void delete(String link) {

        if (read(link).isEmpty()) throw new IllegalArgumentException("cant find to delete: " + link);
        jooq.delete(TV_SERIES)
                .where(TV_SERIES.LINK.equal(link))
                .execute();

    }


}
