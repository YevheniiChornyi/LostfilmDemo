package yevhenii.lostfilmdemo.repository;

import org.jooq.Record;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;

import java.util.List;
import java.util.Optional;

public interface TVSeriesRepository {

    public void create(Record record);

    public Optional<Record> read(String key);

    public List<TvSeriesRecord> readAll();

     void update(TvSeriesRecord record);

    public void delete(String key);

}
