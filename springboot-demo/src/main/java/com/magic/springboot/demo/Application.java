package com.magic.springboot.demo;

import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SerializationUtils;
import org.stagemonitor.core.Stagemonitor;
import org.stagemonitor.web.WebPlugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.*;
import java.util.Random;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by magicdog on 2017/5/17.
 */
@SpringBootApplication
public class Application implements Serializable{

    private static final long serialVersionUID = 42L;

    public static void main(String[] args) throws Exception{
        System.out.println("hello world!");

        Thread t = new Thread(new Runnable() {
            public void run() {
                OutputStream out = null;
                GZIPOutputStream gout = null;
                OutputStreamWriter wr = null;
                BufferedWriter bw = null;

                try {
                    out = new FileOutputStream("d:/tmp/1.txt",true);
                    //gout = new GZIPOutputStream(out);
                    wr = new OutputStreamWriter(out,"UTF-8");
                    bw = new BufferedWriter(wr);


                    for(;;){
                        byte[] bt = SerializationUtils.serialize(newModel());
                        bt = pressGzip(bt);
                        String str = HexUtils.toHexString(bt);
                        bw.write(str);
                        bw.newLine();
                        bw.flush();
                        Thread.currentThread().sleep(3000);
                    }

                }catch (Exception e){

                }finally {
                    try {
                        bw.close();
                        wr.close();
                        //gout.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();

        if(true) return ;
        InputStream in = new FileInputStream("d:/tmp/1.txt");
        //GZIPInputStream gin = new GZIPInputStream(in);
        InputStreamReader ir = new InputStreamReader(in,"utf-8");
        BufferedReader br = new BufferedReader(ir);

        String xs =  "";

        while( true){
            xs = br.readLine();
            if(xs == null){
                Thread.currentThread().sleep(500);
                System.out.println("sleep 500ms ");
            }else{
                byte[] buf = HexUtils.fromHexString(xs);
                Object o = SerializationUtils.deserialize(buf);
                System.out.println(o);
            }
        }


        //Stagemonitor.init();
        //SpringApplication.run(Application.class,args);
    }

    private static byte[] pressGzip(byte[] bt) throws Exception{
        OutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gout = new GZIPOutputStream(bos);
        gout.write(bt);
        gout.flush();
        gout.close();
        return ((ByteArrayOutputStream)bos).toByteArray();
    }

    @Component
    public static class StagemonitorEnabler implements EmbeddedServletContainerCustomizer {
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addInitializers(new ServletContextInitializer() {
                public void onStartup(ServletContext servletContext) throws ServletException {
                    new WebPlugin().onStartup(null, servletContext);
                }
            });
        }
    }

    public static DataModel newModel(){
        Application app = new Application();
        DataModel dm = app.new DataModel();
        dm.setUuid(UUID.randomUUID().toString().replaceAll("-",""));
        dm.setVal(new Random().nextInt());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dm;
    }

    class DataModel implements Serializable{

        private static final long serialVersionUID = 41L;

        private String uuid;
        private int val;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "{ uuid : "+uuid+" , val : " + val + "}";
        }
    }
}
