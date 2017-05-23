package com.magic.zipkin.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by magicdog on 2017/5/23.
 */


@RestController
@RequestMapping("/zipkin/brave/service1")
public class ZipkinBraveController {

    @Autowired
    private OkHttpClient okHttpClient;

    @RequestMapping("/test1")
    public String myboot() throws Exception {
        Thread.sleep(100);//100ms
        Request request = new Request.Builder().url("http://localhost:8032/zipkin/brave/service2/test2").build();
        /*
         * 1、执行execute()的前后，会执行相应的拦截器（cs,cr）
         * 2、请求在被调用方执行的前后，也会执行相应的拦截器（sr,ss）
         */
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

}