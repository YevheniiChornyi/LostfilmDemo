package yevhenii.lostfilmdemo.repository;

import org.jooq.Record;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

import java.util.List;
import java.util.Optional;

public interface TVSeriesRepository {

    void create(Record record);

    Optional<Record> read(String key);

    List<TvSeriesRecord> readAll();

    void update(TvSeriesRecord record);

    void delete(String key);

}
