package com.magic.kafkaspring;

import org.springframework.messaging.Message;
import org.springframework.messaging.converter.StringMessageConverter;

/**
 * Created by magicdog on 2017/6/7.
 */
public class MessageConvert extends StringMessageConverter {


    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint) {
        Object payload = message.getPayload();
        return "1";
        //return (payload instanceof Bar ? payload : new Bar((byte[]) payload));
    }
}
