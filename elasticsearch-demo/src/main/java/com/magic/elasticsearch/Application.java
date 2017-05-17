package com.magic.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Date;

/**
 * Created by magicdog on 2017/5/17.
 */
public class Application {

    private static Logger _logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception{

        _logger.info("start up  , arguments: {}",1);
        // on startup
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        // on shutdown

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("sd",123)
                .field("td",new Date())
                .endObject();
        IndexResponse indexResponse = client.prepareIndex("a","s","d").setSource(builder).get();
        _logger.info(indexResponse.status().toString());
        client.close();
    }
}
