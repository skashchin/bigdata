package com.sk.bigdata.twitter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;

@Slf4j
@Component
public class TwitterMessageProducer {

    @Value("${twitter.poll.delay}")
    private String twitterPollDelay;

    private TwitterClient twitterClient;
    private Producer<Long, String> kafkaProducer;

    @Scheduled(fixedDelayString = "${twitter.poll.delay}")
    public void pollTwits() {
        log.info("Starting polling Twitter messages every {} ms", twitterPollDelay);

        if (!twitterClient.isDone()) {
            List<String> messages = twitterClient.pollAll();
            log.info("Messages size: {}", messages.size());
        }
    }

    @PreDestroy
    public void stopClient() {
        log.info("Stopping twitter client");
        twitterClient.done();
    }

    @Autowired
    public void setTwitterClient(TwitterClient twitterClient) {
        this.twitterClient = twitterClient;
    }

    @Autowired
    public void setKafkaProducer(Producer<Long, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
}