package com.silentgo.json.serializer;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class StringSerializer implements Serializer {
    @Override
    public String serialize(Object object) {
        return "\"" + String.valueOf(object) + "\"";
    }
}
