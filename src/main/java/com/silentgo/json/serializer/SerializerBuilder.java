package com.silentgo.json.serializer;

import com.silentgo.json.exception.SerializerException;
import com.silentgo.json.stream.JSONStream;
import com.silentgo.utils.log.Log;
import com.silentgo.utils.log.LogFactory;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Project : json
 * Package : com.silentgo.json.serializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/20.
 */
public class SerializerBuilder {
    private static final Log LOGGER = LogFactory.get();

    public static final String NULL = "null";

    private StringBuilder stringBuilder;

    public SerializerBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public SerializerBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    public SerializerBuilder(String initialString) {
        this.stringBuilder = new StringBuilder(initialString);
    }

    public SerializerBuilder append(String value) {
        stringBuilder.append(value);
        return this;
    }

    public SerializerBuilder append(Object obj) {
        append(String.valueOf(obj));
        return this;
    }

    public SerializerBuilder append(boolean value) {
        stringBuilder.append(value);
        return this;
    }

    public SerializerBuilder append(char c) {
        stringBuilder.append(c);
        return this;
    }

    public SerializerBuilder append(int i) {
        stringBuilder.append(i);
        return this;
    }

    public SerializerBuilder appendStringSE() {
        stringBuilder.append("\"");
        return this;
    }

    public SerializerBuilder appendObjectInterval() {
        stringBuilder.append(":");
        return this;
    }

    public SerializerBuilder appendObjectStart() {
        stringBuilder.append("{");
        return this;
    }

    public SerializerBuilder appendObjectEnd() {
        stringBuilder.append("}");
        return this;
    }

    public SerializerBuilder appendArrayStart() {
        stringBuilder.append("[");
        return this;
    }

    public SerializerBuilder appendArrayEnd() {
        stringBuilder.append("]");
        return this;
    }

    public SerializerBuilder appendInterval() {
        stringBuilder.append(",");
        return this;
    }

    public SerializerBuilder deleteLastChars(int i) {
        for (int i1 = 0; i1 < i; i1++) {
            deleteLastChar();
        }
        return this;
    }

    public SerializerBuilder deleteLastChar() {
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public SerializerBuilder deleteCharAt(int i) {
        stringBuilder.deleteCharAt(i);
        return this;
    }

    public SerializerBuilder appendNull() {
        stringBuilder.append(NULL);
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public int length() {
        return stringBuilder.length();
    }

}
