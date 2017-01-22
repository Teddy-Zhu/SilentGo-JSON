package com.silentgo.json;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.mapping.JSONEntityMapper;
import com.silentgo.json.mapping.inter.JSONMapper;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONNull;
import com.silentgo.json.parser.ByteParser;
import com.silentgo.json.parser.ByteParserImpl;
import com.silentgo.json.parser.StringParser;
import com.silentgo.json.parser.StringParserImpl;
import com.silentgo.json.serializer.SerializerKit;

import java.util.Collection;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSON {

    public static final JSONConfig config = new JSONConfig();

    public static final JSONConfig oneDepthConfig = new JSONConfig(1);
    public static final JSONMapper mapper = new JSONEntityMapper();

    public static final StringParser stringParser = new StringParserImpl();
    public static final ByteParser byteParser = new ByteParserImpl();

    public static final JSONEntity Null = new JSONNull();


    public static <T> T parse(String text, Class<T> type) {
        return stringParser.parse(text, type, config);
    }

    public static JSONEntity parse(String text) {
        return stringParser.parse(text, config);
    }


    public static <T> T parse(byte[] bytes, Class<T> type) {
        return byteParser.parse(bytes, type, config);
    }

    public static JSONEntity parse(byte[] bytes) {
        return byteParser.parse(bytes, config);
    }

    public static <T> Collection<T> parseCollection(String text, Class<T> type) {
        return stringParser.parseCollection(text, type, config);
    }

    public static String toJSONString(Object object) {
        return SerializerKit.createSerializer(object.getClass()).serialize(object);
    }
}
