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
import com.silentgo.json.serializer.SerializerBuilder;
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


    public static <T> T parse(String text, Class<T> type, JSONConfig jsonConfig) {
        return stringParser.parse(text, type, jsonConfig);
    }

    public static <T> T parse(String text, Class<T> type) {
        return stringParser.parse(text, type, config);
    }

    public static JSONEntity parse(String text, JSONConfig jsonConfig) {
        return stringParser.parse(text, jsonConfig);
    }

    public static JSONEntity parse(String text) {
        return stringParser.parse(text, config);
    }

    public static <T> T parse(byte[] bytes, Class<T> type, JSONConfig jsonConfig) {
        return byteParser.parse(bytes, type, jsonConfig);
    }

    public static <T> T parse(byte[] bytes, Class<T> type) {
        return byteParser.parse(bytes, type, config);
    }

    public static JSONEntity parse(byte[] bytes, JSONConfig jsonConfig) {
        return byteParser.parse(bytes, jsonConfig);
    }

    public static JSONEntity parse(byte[] bytes) {
        return byteParser.parse(bytes, config);
    }

    public static <T> Collection<T> parseCollection(String text, Class<T> type) {
        return stringParser.parseCollection(text, type, config);
    }

    public static String toJSONString(Object object) {
        if (object == null) return SerializerBuilder.NULL;
        return SerializerKit.createSerializer(object.getClass()).serialize(object);
    }

    public static <T> T[] parseArray(String text, Class<T> type) {
        return (T[]) stringParser.parseCollection(text, type, config).toArray();
    }

    public static <T> T[] parseArray(byte[] bytes, Class<T> type) {
        return (T[]) byteParser.parseCollection(bytes, type, config).toArray();
    }


    public static <T> T[] parseArray(String text, Class<T> type, JSONConfig jsonConfig) {
        return (T[]) stringParser.parseCollection(text, type, jsonConfig).toArray();
    }

    public static <T> T[] parseArray(byte[] bytes, Class<T> type, JSONConfig jsonConfig) {
        return (T[]) byteParser.parseCollection(bytes, type, jsonConfig).toArray();
    }

    public static <T> T parse(JSONEntity jsonEntity, Class<T> type) {
        return parse(jsonEntity, type, null);
    }

    public static <T> T parse(JSONEntity jsonEntity, Class<T> type, String name) {
        return (T) mapper.map(jsonEntity, type, name);
    }

    public static <T> T[] parseArray(JSONEntity jsonEntity, Class<T> type) {
        return parseArray(jsonEntity, type, null);
    }

    public static <T> T[] parseArray(JSONEntity jsonEntity, Class<T> type, String name) {
        return (T[]) (mapper.mapCollection(jsonEntity, type, name).toArray());
    }

    public static <T> Collection<T> parseCollection(JSONEntity jsonEntity, Class<T> type) {
        return parseCollection(jsonEntity, type, null);
    }

    public static <T> Collection<T> parseCollection(JSONEntity jsonEntity, Class<T> type, String name) {
        return mapper.mapCollection(jsonEntity, type, name);
    }

}
