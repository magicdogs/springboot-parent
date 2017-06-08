package com.magic.simple.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/*
[user_partner_accounts, users, user_public_accounts, allinpay_transactions, user_auth_info, rights, push_back_reasons, offline_accounts_trans, welab_origin_priority, clie
nt_activities, es.marketing.coupons_users, merchants, weixin_trans_info, lender_config_limit_amt, zhongan_checking, zhongan_checking_result, quark_item_sync, merchant_sho
ps, dues, lender_business_config, educations, zhongan_task_details, liaisons, roles_rights, zhongan_tasks, accounts, allinpay_batch_order, offline_transactions, documents
, i2.marketing.coupons_users, companies, user_quota_versions, partner_white_list, quota_shunts, loans, loan_applications, lender_server_config, user_quotas, roles, credit
_applications, profiles, partner_documents, addresses, notes]
*/
/**
 * Created by magicdog on 2017/6/7.
 */
public class ConsumerClient {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.put("zookeeper.connect", "192.168.110.11:2181/kafka/q-ajxdy5bk");
        props.put("bootstrap.servers", "192.168.110.25:9092");
        props.put("group.id", "test-x1234sx1232");
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "earliest");//earliest,latest
        props.put("offsets.storage", "zookeeper");

        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        //props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test-top1"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
        }
    }
}
