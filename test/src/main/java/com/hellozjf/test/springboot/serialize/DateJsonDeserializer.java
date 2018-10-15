package com.hellozjf.test.springboot.serialize;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jingfeng Zhou
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {
    public static final SimpleDateFormat format = new SimpleDateFormat(DateSerializeString.FORMAT);
    private static final Logger log = LoggerFactory.getLogger(DateJsonDeserializer.class);

    @Override
    public Date deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, DeserializationContext deserializationContext) {

        try {
            if (jsonParser != null && StringUtils.isNotEmpty(jsonParser.getText())) {
                return format.parse(jsonParser.getText());
            } else {
                return null;
            }

        } catch (Exception e) {
            log.error("反序列化出现错误：{}", e);
            throw new RuntimeException(e);
        }
    }
}
