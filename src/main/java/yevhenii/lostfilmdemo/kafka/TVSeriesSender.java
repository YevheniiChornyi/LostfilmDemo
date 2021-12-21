package yevhenii.lostfilmdemo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import yevhenii.lostfilmdemo.entity.TVSeries;

@Slf4j
@Component
@RequiredArgsConstructor
public class TVSeriesSender {

    private final KafkaTemplate<String, TVSeries> kafkaTemplate;

    public void send(String topic, TVSeries payload) {

        log.debug("sending payload: {} to topic called {}", payload, topic);
        kafkaTemplate.send(topic, payload);
    }
}
