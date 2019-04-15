package com.hellozjf.test.cassandra.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.hellozjf.test.cassandra.domain.Csadstate;
import com.hellozjf.test.cassandra.repository.CsadstateRepository;
import com.hellozjf.test.cassandra.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jingfeng Zhou
 */
@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CsadstateRepository csadstateRepository;

    private String readFromClasspathFile(String filename) {
        ClassPathResource classPathResource = new ClassPathResource("unitConfig.js");
        try (
                InputStream inputStream = classPathResource.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            log.error("e = {}", e);
            return null;
        }
    }

    @Override
    public String getName(String groupid) {
        try {
            String unitConfig = readFromClasspathFile("unitConfig.js");
            JsonNode jsonNode = objectMapper.readTree(unitConfig);
            if (jsonNode.isArray()) {
                ArrayNode arrayNode = (ArrayNode) jsonNode;
                Iterator<JsonNode> iterator = arrayNode.elements();
                while (iterator.hasNext()) {
                    JsonNode node = iterator.next();
                    if (node.get("unitCode").textValue().equalsIgnoreCase(groupid)) {
                        return node.get("unitName").textValue();
                    }
                }
            }
            return null;
        } catch (IOException e) {
            log.error("e = {}", e);
            return null;
        }
    }

    @Override
    public List<String> getCsadList(String groupid) {
        List<Csadstate> csadstateList = csadstateRepository.findByGroupid(groupid);
        return csadstateList.stream()
                .map(csadstate -> csadstate.getCsadid())
                .collect(Collectors.toList());
    }
}
