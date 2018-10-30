package com.hellozjf.test.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellozjf.test.springboot.dataobject.HelloObject;
import com.hellozjf.test.springboot.util.ZooKeeperConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author Jingfeng Zhou
 */
@Configuration
@Slf4j
public class BeanConfig {

    public static final String MY_FIRST_ZNODE = "/MyFirstZnode";
    public static final String ALIYUN_HELLOZJF_COM = "aliyun.hellozjf.com";

    @Bean("helloObject")
    public HelloObject helloObject() {
        HelloObject helloObject = new HelloObject();
        helloObject.setB(new Byte("100"));
        helloObject.setC('C');
        helloObject.setD(12.56);
        helloObject.setF(1.2f);
        helloObject.setFlag(false);
        helloObject.setI(1024);
        helloObject.setId(11111111L);
        helloObject.setName("hello");
        helloObject.setTime(new Date());
        helloObject.setData(new byte[] {(byte) 0x01, (byte) 0x02});
        return helloObject;
    }

    @Bean("helloObject2")
    public HelloObject helloObject2() {
        HelloObject helloObject = new HelloObject();
        helloObject.setB(new Byte("-100"));
        helloObject.setC('D');
        helloObject.setD(78.56);
        helloObject.setF(123.42f);
        helloObject.setFlag(true);
        helloObject.setI(1024324);
        helloObject.setId(11111222L);
        helloObject.setName("helloworld");
        helloObject.setTime(new Date());

        byte[] data = new byte[1024];
        for (int i = 0; i < 1024; i++) {
            data[i] = (byte) i;
        }
        helloObject.setData(data);
        return helloObject;
    }

    @Bean("helloObjectList")
    public List<HelloObject> helloObjectList() {
        List<HelloObject> helloObjectList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String s = String.valueOf(i);
            HelloObject helloObject = new HelloObject();
            helloObject.setB(Byte.valueOf(s));
            helloObject.setC(Character.forDigit(i, 10));
            helloObject.setD(Double.valueOf(s));
            helloObject.setF(Float.valueOf(s));
            helloObject.setFlag(i % 2 == 0 ? true : false);
            helloObject.setI(i);
            helloObject.setId((long) i * i * i);
            helloObject.setName(s);
            helloObject.setTime(new Date());
            helloObjectList.add(helloObject);
        }
        return helloObjectList;
    }

    private void printMd() throws Exception {
        // 读取test.md
        Resource resource = new ClassPathResource("static/md/test.md");
        InputStream in = resource.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String str = null;
        // 我需要一个Map，内容是测试1:```，测试1 -> 测试11:```……
        Map<String, String> contentMap = new HashMap<>();
        String title = null;
        StringBuilder content = new StringBuilder();
        // 最多六个标题
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            titles.add("");
        }
        boolean bBegin = false;
        while ((str = reader.readLine()) != null) {
            if (str.startsWith("#")) {
                if (title != null) {
                    contentMap.put(title, content.toString());
                    content.delete(0, content.length());
                }
                // 一级标题是#，存titles[0]，即level=1，二级标题是##，存titles[1]，即level=2
                String replaceStr = str.replace("#", "");
                int level = str.length() - replaceStr.length();
                titles.set(level - 1, replaceStr.trim());
                StringBuilder titleBuilder = new StringBuilder();
                String sign = " -> ";
                for (int i = 0; i < level; i++) {
                    titleBuilder.append(titles.get(i) + sign);
                }
                titleBuilder.delete(titleBuilder.length() - sign.length(), titleBuilder.length());
                title = titleBuilder.toString();
            } else {
                if (str.equals("```")) {
                    bBegin = !bBegin;
                    continue;
                }
                if (bBegin) {
                    content.append(str + "\n");
                }
            }
        }
        if (title != null) {
            contentMap.put(title, content.toString());
            content.delete(0, content.length());
        }

        Set<String> keySet = contentMap.keySet();
        for (String key : keySet) {
            log.debug("key={} value={}", key, contentMap.get(key));
        }
    }

    private void testUrlEncoding() throws Exception {
        // 测试URL编码
        String hello = "你好";
        String encodeHello = URLEncoder.encode(hello, "UTF-8");
        String decodeHello = URLDecoder.decode(encodeHello, "UTF-8");
        log.debug("encodeHello = {}, decodeHello = {}", encodeHello, decodeHello);
    }

    private void testTime() throws Exception {
        // 测试时间
        Date date = new Date();
        log.debug("date = {}", date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.sss");
        log.debug("dateString = {}", simpleDateFormat.format(date));
    }

    private void testUUID() throws Exception {
        // 测试UUID
        UUID uuid = UUID.randomUUID();
        log.debug("randomUUID value={}, version={}", uuid.toString(), uuid.version());
    }

    private void testList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.add(0, 11);
        log.debug("list={}", list);
    }

    private void printFileEncoding() {
        String fileEncoding = System.getProperty("file.encoding");
        log.debug("fileEncoding={}", fileEncoding);
    }

    private void testZkCreate() {
        try {
            String path = MY_FIRST_ZNODE;
            byte[] data = "My first zookeeper app".getBytes();
            ZooKeeper zooKeeper = ZooKeeperConnectionUtils.connect(ALIYUN_HELLOZJF_COM);
            zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.close();

        } catch (Exception e) {
            log.error("testZkCreate error e={}", e);
        }
    }

    private void testZkExists() {
        try {
            ZooKeeper zooKeeper = ZooKeeperConnectionUtils.connect(ALIYUN_HELLOZJF_COM);
            Stat stat = zooKeeper.exists(MY_FIRST_ZNODE, true);
            if (stat != null) {
                log.debug("Node exists and the node version is {}", stat.getVersion());
            } else {
                log.debug("Node does not exists");
            }
        } catch (Exception e) {
            log.error("testZkExists error e={}", e);
        }
    }

    private void testZkGetData() {
        try {
            String path = MY_FIRST_ZNODE;
            CountDownLatch connectedSignal = new CountDownLatch(1);
            ZooKeeper zooKeeper = ZooKeeperConnectionUtils.connect(ALIYUN_HELLOZJF_COM);
            Stat stat = zooKeeper.exists(path, true);
            if (stat != null) {
                byte[] b = zooKeeper.getData(path, watchEvent -> {
                    if (watchEvent.getType() == Watcher.Event.EventType.None) {
                        switch (watchEvent.getState()) {
                            case Expired:
                                connectedSignal.countDown();
                                break;
                        }
                    } else {
                        try {
                            byte[] bn = zooKeeper.getData(path, false, null);
                            String data = new String(bn, "UTF-8");
                            log.debug("data={}", data);
                            connectedSignal.countDown();
                        } catch (Exception e) {
                            log.error("getData error e = {}", e);
                        }
                    }
                }, null);

                String data = new String(b, "UTF-8");
                log.debug("data={}", data);
                connectedSignal.await();
            }
        } catch (Exception e) {
            log.error("testZkExists error e={}", e);
        }
    }

    private void testZkSetData() {
        try {
            String path = MY_FIRST_ZNODE;
            byte[] data = "Success".getBytes();
            ZooKeeper zooKeeper = ZooKeeperConnectionUtils.connect(ALIYUN_HELLOZJF_COM);
            int version = zooKeeper.exists(MY_FIRST_ZNODE, true).getVersion();
            log.debug("version={}", version);
            Stat stat = zooKeeper.setData(path, data, version);
            log.debug("setData stat={}", stat);
        } catch (Exception e) {
            log.error("testZkSetData error e={}", e);
        }
    }

    private void testZkGetChildren() {
        try {
            String path = MY_FIRST_ZNODE;
            ZooKeeper zooKeeper = ZooKeeperConnectionUtils.connect(ALIYUN_HELLOZJF_COM);
            Stat stat = zooKeeper.exists(path, true);
            if (stat != null) {
                List<String> children = zooKeeper.getChildren(path, false);
                for (String child : children) {
                    log.debug("child={}", child);
                }
            } else {
                log.debug("Node does not exist");
            }
        } catch (Exception e) {
            log.error("testZkGetChildren error e={}", e);
        }
    }

    private void testZkDelete() {
        try {
            String path = MY_FIRST_ZNODE;
            ZooKeeper zooKeeper = ZooKeeperConnectionUtils.connect(ALIYUN_HELLOZJF_COM);

            // 删除MY_FIRST_ZNODE节点下面的子节点，这里只考虑了一层子节点，多层子节点不会写
            List<String> children = zooKeeper.getChildren(MY_FIRST_ZNODE, false);
            for (String child : children) {
                String fullChildNodeName = MY_FIRST_ZNODE + "/" + child;
                int version = zooKeeper.exists(fullChildNodeName, false).getVersion();
                zooKeeper.delete(fullChildNodeName, version);
            }

            // 删除MY_FIRST_ZNODE节点
            int version = zooKeeper.exists(MY_FIRST_ZNODE, true).getVersion();
            zooKeeper.delete(path, version);
        } catch (Exception e) {
            log.error("testZkDelete error e={}", e);
        }
    }

    private void testZooKeeper() {
//            testZkCreate();
        testZkExists();
//            testZkGetData();
        testZkSetData();
        testZkGetChildren();
        testZkDelete();
    }

    private void testListRemove() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }
        for (int i = 0; i < integerList.size(); i++) {
            Integer t = integerList.get(i);
            if (t % 3 == 0) {
                // 移除能被3整除的元素
                integerList.remove(t);
                // 因为移除了元素，所以坐标也要减一
                i--;
            }
        }
        for (Integer i : integerList) {
            log.debug("{}", i);
        }
    }


    private void testClassLocation() {
        try {
            URL location = BeanConfig.class.getProtectionDomain().getCodeSource().getLocation();
            String path = location.toURI().toString();
            System.out.println(path);
        } catch (URISyntaxException e) {
            log.error("{}", e);
        }
    }

    private void testIteratorRemove() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            integerList.add(i);
        }

        for (Iterator<Integer> iter = integerList.iterator(); iter.hasNext(); ) {
            Integer t = iter.next();
            if (t % 3 == 0) {
                iter.remove();
            }
        }

        for (Integer i : integerList) {
            log.debug("{}", i);
        }
    }

    @Bean
    public CommandLineRunner commandLineRunner(@Qualifier("helloObject2") final HelloObject helloObject,
                                               @Qualifier("helloObjectList") final List<HelloObject> helloObjectList,
                                               JsonProperties jsonProperties) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            log.debug("helloObject = {}", objectMapper.writeValueAsString(helloObject));
            log.debug("helloObjectList = {}", objectMapper.writeValueAsString(helloObjectList));

            testUrlEncoding();
            testTime();
            testUUID();
            printMd();
            printFileEncoding();
            testList();
//            testZooKeeper();
//            testListRemove();
            testIteratorRemove();
            testClassLocation();
            testPrintClass();
            testJsonProperties(jsonProperties);
        };
    }

    private void testJsonProperties(JsonProperties jsonProperties) {
        log.debug("host={}", jsonProperties.getHost());
        log.debug("port={}", jsonProperties.getPort());
        log.debug("resend={}", jsonProperties.isResend());
        log.debug("topics={}", jsonProperties.getTopics());
        log.debug("useName={}", jsonProperties.getUseName());
        log.debug("portPassword={}", jsonProperties.getPortPassword());
        log.debug("none={}", jsonProperties.getNone());
    }

    private void testPrintClass() {
        Object object = new BeanConfig();
        log.debug(object.getClass().toString());
        log.debug(object.getClass().toGenericString());
    }
}
