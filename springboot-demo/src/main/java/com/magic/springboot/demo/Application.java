package com.magic.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by magicdog on 2017/5/17.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("hello world!");
        SpringApplication.run(Application.class,args);
    }
}
