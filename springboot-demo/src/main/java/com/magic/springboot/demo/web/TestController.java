package com.magic.springboot.demo.web;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by magicdog on 2017/5/23.
 */
@RestController
public class TestController {


    @GetMapping(value = "/hello")
    public String hello(){
        /*Transaction t = Cat.newTransaction("URL","hello");
        Cat.logEvent("URL.SERVER","TestController", Event.SUCCESS,"a=1&b=2");
        Cat.logMetricForCount("payCount");
        Cat.logMetricForSum("payAmount",1.0d);*/
        try {
            Thread.currentThread().sleep(1000L);
            //t.setStatus(Transaction.SUCCESS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //t.setStatus(e);
        }finally {
            //t.complete();
        }

        System.out.println("hello");
        return "hello";
    }

}
