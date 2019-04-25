package com.hellozjf.test.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootApplicationTest {

    @Test
    public void testCookieOrigin() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpClientContext context = HttpClientContext.create();
        HttpGet httpget = new HttpGet("http://www.baidu.com/");
        CloseableHttpResponse response = httpclient.execute(httpget, context);
        Header[] hs = response.getHeaders("Set-Cookie");
        log.debug("hs = {}", hs);
        CookieOrigin cookieOrigin = context.getCookieOrigin();
        CookieSpec cookieSpec = context.getCookieSpec();
        CookieStore cookieStore = context.getCookieStore();
        log.debug("cookieOrigin = {}", cookieOrigin);
        log.debug("cookieSpec = {}", cookieSpec);
        log.debug("cookieStore = {}", cookieStore);

        List<Cookie> cookieList = cookieStore.getCookies();
        log.debug("cookieList = {}", cookieList);
    }
}