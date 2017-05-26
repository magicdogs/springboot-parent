package com.magic.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;
import org.stagemonitor.core.Stagemonitor;
import org.stagemonitor.web.WebPlugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by magicdog on 2017/5/17.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("hello world!");
        Stagemonitor.init();
        SpringApplication.run(Application.class,args);
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
}
