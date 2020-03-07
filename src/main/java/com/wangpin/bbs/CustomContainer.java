package com.wangpin.bbs;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @description 对内置Tomcat服务器进行自定义设置，允许出现某些字符
 * @author
 */
@Component
public class CustomContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        // 设置路径允许出现字符 [ 和 ]
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            connector.setAttribute("relaxedQueryChars", "[]");
            connector.setAttribute("relaxedPathChars", "[]");
        });
    }
}