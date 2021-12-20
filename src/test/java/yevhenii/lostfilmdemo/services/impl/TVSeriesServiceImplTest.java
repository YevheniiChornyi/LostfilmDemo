package yevhenii.lostfilmdemo.services.impl;

import org.jooq.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import yevhenii.lostfilmdemo.convertors.TVRecordConvertor;
import yevhenii.lostfilmdemo.entity.TVSeries;
import yevhenii.lostfilmdemo.jooq.generated.tables.records.TvSeriesRecord;
import yevhenii.lostfilmdemo.kafka.TVSeriesSender;
import yevhenii.lostfilmdemo.repository.impl.TVSeriesRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TVSeriesServiceImplTest {
    @Mock
    TVSeriesRepositoryImpl repository;
    @InjectMocks
    TVSeriesServiceImpl service;
    @Spy
    TVRecordConvertor convertor;
    @Mock
    TVSeriesSender sender;

    private static TVSeries series;

    @BeforeAll
    static void init(){
        series = TVSeries.builder()
                .lastUpdate("update").episode(1)
                .image("image").russianName("rname")
                .name("name").season(2)
                .link("link").build();
    }
    @Test
    void save() {

        doNothing().when(repository).create(isA(Record.class));
        doReturn(Optional.empty()).when(repository).read(isA(String.class));

        doNothing().when(sender).send(isA(String.class), isA(TVSeries.class));

        service.save(series);
        verify(repository, times(1)).create(convertor.createRecord(series));
        verify(sender, times(1)).send(any(), eq(series));

    }

    @Test
    void saveException(){

        Assertions.assertThrows(NullPointerException.class,()->{ service.save(TVSeries.builder().build());
        });
    }

    @Test
    void  update(){

        doReturn(Optional.of(series)).when(repository).read(isA(String.class));
        service.save(series);
        verify(repository, times(1)).read(series.getLink());
        verify(repository, times(0)).create(convertor.createRecord(series));
        verify(sender, times(1)).send(any(), eq(series));
    }

    @Test
    void delete() {
        doNothing().when(repository).delete(isA(String.class));
        service.delete(series.getLink());
        verify(repository, times(1)).delete(eq(series.getLink()));
    }

    @Test
    void find() {
        service.find(series.getLink());
        verify(repository, times(1)).read(series.getLink());
    }

    @Test
    void findAll() {
        List<TvSeriesRecord> records = new ArrayList<>();
        doReturn(records).when(repository).readAll();
        service.findAll();
        verify(repository, times(1)).readAll();
    }
}