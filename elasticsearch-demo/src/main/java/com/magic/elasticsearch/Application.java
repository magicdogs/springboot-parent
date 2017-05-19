package com.magic.elasticsearch;

import com.floragunn.searchguard.ssl.SearchGuardSSLPlugin;
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

        Settings settings = Settings.builder()
                //.put("elasticsearch.cluster-nodes","192.168.104.101:9300")
                //.put("elasticsearch.cluster-name","app-elk")
                .put("searchguard.ssl.transport.enabled",true)
                .put("searchguard.ssl.transport.keystore_filepath","sgadmin-keystore.jks")
                .put("searchguard.ssl.transport.keystore_password","671221a7fe53860f56ca")
                .put("searchguard.ssl.transport.truststore_filepath","truststore.jks")
                .put("searchguard.ssl.transport.truststore_password","e4780b3a3d2a7a67514c")
                .put("path.home","d:/tmp/logs")
                .put("searchguard.ssl.transport.enforce_hostname_verification",false)
                //searchguard.ssl.transport.resolve_hostname: false
                .build();

        // on startup
        TransportClient client = new PreBuiltTransportClient(settings, SearchGuardSSLPlugin.class)
                //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.104.101"), 9300));
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
