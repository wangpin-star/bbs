package com.wangpin.bbs;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes  extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        if ((Integer) map.get("status") == 500) {
            map.put("message", "500！服务器内部错误!");
        }
        if ((Integer) map.get("status") == 404) {
            map.put("message", "404!找不到页面!");
        }
        if ((Integer) map.get("status") == 400) {
            map.put("message", "400!请求出错");
        }
        return map;
    }
}
