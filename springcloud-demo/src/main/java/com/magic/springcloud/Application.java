package com.magic.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import zipkin.server.EnableZipkinServer;
import zipkin.server.RegisterZipkinHealthIndicators;
import zipkin.server.ZipkinServer;

/**
 * Created by magicdog on 2017/5/23.
 */
@SpringBootApplication
@EnableZipkinServer
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run(Application.class,args);
        new SpringApplicationBuilder(new Object[]{ZipkinServer.class})
                .listeners(new ApplicationListener[]{new RegisterZipkinHealthIndicators()}).properties(new String[]{"spring.config.name=zipkin-server"}).run(args);
    }
}
