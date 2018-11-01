package com.hellozjf.test.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellozjf.test.springboot.SpringContextUtil;
import com.hellozjf.test.springboot.dao.HelloObjectRepository;
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

import javax.transaction.Transactional;
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

            testGetBean();
            testRegular();
            testRuntime();
        };
    }

    private void testRuntime() throws Exception {
        Process process = Runtime.getRuntime().exec("cmd /k netstat -ano | findstr 135");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        log.debug("testRuntime");
        while ((line = br.readLine()) != null) {
            log.debug("{}", line);
        }
    }

    /**
     * 普通方法上面加了@Transactional根本没用！！！
     * @throws Exception
     */
    @Transactional(rollbackOn = Exception.class)
    protected void testGetBean() throws Exception {
        Object object = SpringContextUtil.getBean("helloObjectRepository");
        if (object == null) {
            log.debug("testGetBean failed");
        } else {
            if (object instanceof HelloObjectRepository) {
                log.debug("testGetBean success");

                // 我再试试非上下文里面能不能用事务
//                HelloObjectRepository helloObjectRepository = (HelloObjectRepository) object;
//
//                HelloObject helloObject = new HelloObject();
//                helloObject.setName(RandomStringUtils.randomAlphanumeric(99));
//                helloObjectRepository.save(helloObject);
//                HelloObject helloObject2 = new HelloObject();
//                helloObject2.setName(RandomStringUtils.randomAlphanumeric(300));
//                helloObjectRepository.save(helloObject2);
            }
        }
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

    private void testRegular() {
        String regular = "(!超市)(中的奖|中奖)(个税)";
        String text = "银行抽中的奖要交个税么？";
        boolean bFit = fitFullRegular(text, regular);
        if (bFit) {
            log.debug("文本符合规则");
        } else {
            log.debug("文本不符合规则");
        }
    }

    /**
     * 判断文本是否符合规则
     * 问题1：规则要不要考虑顺序
     * 问题2：括号里面会不会有括号
     * 问题3：或和非会不会同时存在
     * 问题4：文件内容会不会很大
     * 问题5：规则要不要考虑跳过的字节
     * @param text 例如"超市抽中的奖要交个税么？"
     * @param regular 例如"(!超市)(中的奖|中奖)(个税)"
     * @return
     */
    private boolean fitFullRegular(String text, String regular) {

        // 假设括号不会重叠，那么根据括号分成多组
        List<String> regularList = splitRegularByBracket(regular);
        log.debug("regularList = {}", regularList);

        // 将规则组的每个项目都应用到text上面，最外层肯定是与规则
        boolean bFit = true;
        for (String reg : regularList) {
            if (! fitSplitRegular(text, reg)) {
                bFit = false;
                break;
            }
        }
        return bFit;
    }

    private boolean fitSplitRegular(String text, String reg) {
        if (reg.startsWith("!")) {
            // 非规则
            String r = reg.substring(1);
            if (text.contains(r)) {
                return false;
            } else {
                return true;
            }
        } else if (reg.contains("|")) {
            // 或规则
            String[] rs = reg.split("|");
            for (String r : rs) {
                if (text.contains(r)) {
                    return true;
                }
            }
            return false;
        } else {
            return text.contains(reg);
        }
    }

    private List<String> splitRegularByBracket(String regular) {
        boolean bInBracket = false;
        List<String> returnList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < regular.length(); i++) {
            char c = regular.charAt(i);
            if (c == '(') {
                bInBracket = true;
            } else if (c == ')') {
                bInBracket = false;
                // 然后将stringBuilder的字符串放到returnList里面
                returnList.add(stringBuilder.toString());
                // 清空stringBuilder
                stringBuilder.setLength(0);
            } else {
                if (bInBracket) {
                    stringBuilder.append(c);
                }
            }
        }
        return returnList;
    }
}
