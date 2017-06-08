package com.magic.kafkaspring;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by magicdog on 2017/6/7.
 */
public interface KafkaTopicChannel {

    String TEST = "test-top1";

    @Input(TEST)
    SubscribableChannel test();

}
