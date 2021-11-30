package yevhenii.lostfilmdemo.repository;

import org.jooq.Record;

import java.util.List;
import java.util.Optional;

public interface JooqRepository<K> {

    public void create(Record record);

    public Optional<Record> read(K key);

    public List<Record> readAll();

    void update(Record record, K key);

    public void delete(K key);

}
