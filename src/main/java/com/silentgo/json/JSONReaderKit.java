package com.silentgo.json;

import com.silentgo.json.common.Key;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.*;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONReaderKit {

    public static JSONEntity readJSONObject(JSONReader reader, JSONConfig jsonConfig, JSONObject outJsonObject) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipObject(reader);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONObject.class);
        }

        JSONObject jsonObject = outJsonObject == null ? new JSONObject() : outJsonObject;
        boolean isFirst = true;
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\b':
                case '\n':
                case '\r': {
                    continue;
                }
                default: {
                    throw JSONReport.error(reader, "parse object error , can not found key start");
                }
                case Key.STRING_SPLIT: {
                    isFirst = false;
                    int pos = reader.getPos();
                    JSONSkipKit.skipString(reader);
                    String name = new String(reader.getData(), pos, reader.getPos() - pos);

                    byte key = nextWord(reader);
                    if (Key.VALUE_COL != key)
                        throw JSONReport.error(reader, "can not found value");
                    JSONEntity jsonEntity = JSONReaderKit.readJSONValue(reader, jsonConfig);
                    jsonObject.put(name, jsonEntity);
                    continue;
                }
                case Key.OBJECT_SPLIT: {
                    if (isFirst) throw JSONReport.error(reader, "unexcepted , begin the object");
                    continue;
                }
                case Key.OBJECT_END: {
                    return jsonObject;
                }
            }
        }
        throw JSONReport.error(reader, "can not found object closure }");
    }

    public static JSONEntity readJSONArray(JSONReader reader, JSONConfig jsonConfig, JSONArray outJsonArray) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipArray(reader);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONArray.class);
        }

        JSONArray jsonArray = outJsonArray == null ? new JSONArray() : outJsonArray;
        boolean isFirst = true;
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\b':
                case '\n':
                case '\r': {
                    continue;
                }
                case Key.OBJECT_SPLIT: {
                    if (isFirst) {
                        jsonArray.push(new JSONNull());
                    }
                    isFirst = false;
                    continue;
                }
                case Key.OBJECT_START: {
                    isFirst = false;
                    jsonArray.push(JSONReaderKit.readJSONObject(reader, jsonConfig, null));
                    continue;
                }
                case Key.ARRAY_START: {
                    isFirst = false;
                    jsonArray.push(JSONReaderKit.readJSONArray(reader, jsonConfig, null));
                    continue;
                }
                case Key.NUMBER_VAL_SIGN:
                case Key.NUMBER_VAL0:
                case Key.NUMBER_VAL1:
                case Key.NUMBER_VAL2:
                case Key.NUMBER_VAL3:
                case Key.NUMBER_VAL4:
                case Key.NUMBER_VAL5:
                case Key.NUMBER_VAL6:
                case Key.NUMBER_VAL7:
                case Key.NUMBER_VAL8:
                case Key.NUMBER_VAL9:
                case Key.NUMBER_INTERVAL: {
                    //number
                    jsonArray.push(JSONReaderKit.readJSONNumber(reader, jsonConfig));
                    continue;
                }
                case Key.STRING_SPLIT: {
                    jsonArray.push(JSONReaderKit.readJSONString(reader, jsonConfig));
                    isFirst = false;
                    continue;
                }
                case Key.ARRAY_END: {
                    return jsonArray;
                }
                case Key.BOOL_FALSE: {
                    return JSONReaderKit.readJSONBool(reader, jsonConfig, "false");
                }
                case Key.BOOL_TRUE: {
                    return JSONReaderKit.readJSONBool(reader, jsonConfig, "true");
                }
            }
        }

        throw JSONReport.error(reader, "can not found array closure ]");
    }

    public static JSONEntity readJSONValue(JSONReader reader, JSONConfig jsonConfig) {
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    continue;
                case Key.NUMBER_VAL_SIGN:
                case Key.NUMBER_VAL0:
                case Key.NUMBER_VAL1:
                case Key.NUMBER_VAL2:
                case Key.NUMBER_VAL3:
                case Key.NUMBER_VAL4:
                case Key.NUMBER_VAL5:
                case Key.NUMBER_VAL6:
                case Key.NUMBER_VAL7:
                case Key.NUMBER_VAL8:
                case Key.NUMBER_VAL9:
                case Key.NUMBER_INTERVAL: {
                    //number
                    return JSONReaderKit.readJSONNumber(reader, jsonConfig);
                }
                case Key.STRING_SPLIT: {
                    //string

                    return JSONReaderKit.readJSONString(reader, jsonConfig);
                }
                case Key.OBJECT_START: {
                    //object {}
                    return JSONReaderKit.readJSONObject(reader, jsonConfig, null);
                }
                case Key.ARRAY_START: {
                    //array []
                    return JSONReaderKit.readJSONArray(reader, jsonConfig, null);
                }
                case Key.NULL: {
                    //null
                    return JSONReaderKit.readJSONNull(reader, jsonConfig);
                }
                case Key.BOOL_FALSE: {
                    return JSONReaderKit.readJSONBool(reader, jsonConfig, "false");
                }
                case Key.BOOL_TRUE: {
                    return JSONReaderKit.readJSONBool(reader, jsonConfig, "true");
                }
                default:
                    throw JSONReport.error(reader, "can not handle this value");
            }
        }
        throw JSONReport.error(reader, "unknown format string");
    }

    public static JSONEntity readJSONBool(JSONReader reader, JSONConfig jsonConfig, String val) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipStringArg(reader, val, true);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONBool.class);
        }
        int i = reader.getPos();
        JSONSkipKit.skipBlank(reader);
        String value = new String(reader.getData(), i, reader.getPos() - i);

        return new JSONBool(value);
    }

    public static JSONEntity readJSONNull(JSONReader reader, JSONConfig jsonConfig) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipStringArg(reader, "null", true);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONNull.class);
        }
        int i = reader.getPos();
        JSONSkipKit.skipBlank(reader);
        String value = new String(reader.getData(), i, reader.getPos() - i);

        return new JSONNull(value);
    }

    public static JSONEntity readJSONNumber(JSONReader reader, JSONConfig jsonConfig) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            boolean isDecimal = JSONSkipKit.skipNumber(reader);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, isDecimal ? JSONDouble.class : JSONLong.class);
        }
        int i = reader.getPos();
        boolean isDecimal = JSONSkipKit.skipNumber(reader);
        String value = new String(reader.getData(), i, reader.getPos() - i);

        if (isDecimal) {
            return new JSONDouble(value);
        }
        return new JSONLong(value);
    }

    public static JSONEntity readJSONString(JSONReader reader, JSONConfig config) {
        if (config.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipString(reader);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONString.class);
        }
        int i = reader.getPos() + 1;
        JSONSkipKit.skipString(reader);
        String value = new String(reader.getData(), i, reader.getPos() - i);

        return new JSONString(value);
    }

    private static byte nextWord(JSONReader reader) {
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    continue;
                default:
                    return b;
            }
        }
        return JSONReader.BYTE_NULL;
    }
}
