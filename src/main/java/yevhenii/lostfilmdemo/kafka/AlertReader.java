package yevhenii.lostfilmdemo.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertReader {
//    @Value("${lostfilm.topic}")
//    private final String topicName;

    @KafkaListener(topics = {"${lostfilm.topic}"})
    public void receive(ConsumerRecord<?, ?> consumerRecord){
        log.info("received: {}", consumerRecord.toString());
    }
}
