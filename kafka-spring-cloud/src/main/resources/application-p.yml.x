
spring:
    cloud:
        stream:
            kafka:
                binder:
                    brokers: 192.168.110.25:9092
                    zkNodes: 192.168.110.11:2181/kafka/q-ajxdy5bk
                    configuration:
                         key.deserializer:  org.apache.kafka.common.serialization.StringDeserializer
                         value.deserializer:  org.apache.kafka.common.serialization.StringDeserializer

            bindings.test-top1:
                destination:  test-top1
                #group: test-x1234
                consumer.headerMode: raw
                contentType: 'text/plain'
                consumer:
                    configuration:
                        key.deserializer: org.apache.kafka.common.serialization.StringDeserializer
                        value.deserializer: org.apache.kafka.common.serialization.StringDeserializer
                configuration:
                      key.deserializer: org.apache.kafka.common.serialization.StringDeserializer
                      value.deserializer: org.apache.kafka.common.serialization.StringDeserializer
