package com.magic.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by magicdog on 2017/5/23.
 */
//mysql run
//java -DSTORAGE_TYPE=mysql -DMYSQL_USER=root -DMYSQL_PASS=123456 -jar zipkin.jar
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
