package com.magic.shell;

import kafka.tools.ExportZkOffsets;
import kafka.tools.GetOffsetShell;
import org.apache.commons.io.IOUtils;
import org.springframework.shell.Bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * Created by magicdog on 2017/5/18.
 */
public class Applicaton {
    public static void main(String[] args) throws Exception{

        //Bootstrap.main(args);

        //GetOffsetShell.main(args);
        //ExportZkOffsets.main(args);

        //proxy();
    }

    private static void proxy() throws Exception{
        URL url = new URL("https://www.baidu.com");
        // 创建代理服务器
        InetSocketAddress addr = new InetSocketAddress("192.129.239.206",9001);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
        URLConnection conn = url.openConnection(proxy);

        //以下三行是在需要验证时，输入帐号密码信息
        String headerkey = "Proxy-Authorization";
        //String headerValue = "Basic "+ Base64.encodeToString("atco:atco".getBytes(), false); //帐号密码用:隔开，base64加密方式
        //conn.setRequestProperty(headerkey, headerValue);

        InputStream in = conn.getInputStream();
        // InputStream in = url.openStream();
        String s = IOUtils.toString(in, "utf-8");
        System.out.println(s);
    }


}
