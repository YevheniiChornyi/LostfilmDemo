package yevhenii.lostfilmdemo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload){

        log.info("sending payload: {} to topic called {}", payload, topic);
        kafkaTemplate.send(topic, payload);
    }
}
