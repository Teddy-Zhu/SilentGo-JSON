package com.silentgo.json.parser;

import com.silentgo.json.report.JSONReport;
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
                    new JSONReport().report(reader, "parse object error , can not found key start");
                }
                case Key.STRING_SPLIT: {
                    isFirst = false;
                    int pos = reader.getPos() + 1;
                    JSONSkipKit.skipString(reader);
                    String name = new String(reader.getData(), pos, reader.getPos() - pos);

                    byte key = nextWord(reader);
                    if (Key.VALUE_COL != key)
                        new JSONReport().report(reader, "can not found value");
                    JSONEntity jsonEntity = JSONReaderKit.readJSONValue(reader, jsonConfig);
                    jsonObject.put(name, jsonEntity);
                    continue;
                }
                case Key.OBJECT_SPLIT: {
                    if (isFirst) new JSONReport().report(reader, "unexcepted , begin the object");
                    continue;
                }
                case Key.OBJECT_END: {
                    return jsonObject;
                }
            }
        }
        new JSONReport().report(reader, "can not found object closure }");
        return null;
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
                    isFirst = false;
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
                    jsonArray.push(JSONReaderKit.readJSONBool(reader, jsonConfig, "false"));
                    isFirst = false;
                    continue;
                }
                case Key.BOOL_TRUE: {
                    jsonArray.push(JSONReaderKit.readJSONBool(reader, jsonConfig, "true"));
                    isFirst = false;
                    continue;
                }
            }
        }
        new JSONReport().report(reader, "can not found array closure ]");
        return null;
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
                    new JSONReport().report(reader, "can not handle this value");
            }
        }
        new JSONReport().report(reader, "unknown format string");
        return null;
    }

    public static JSONEntity readJSONBool(JSONReader reader, JSONConfig jsonConfig, String val) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipStringArg(reader, val, true);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONBool.class);
        }

        String value;
        if (jsonConfig.isHasSkipped()) {
            value = getString(reader);
        } else {
            int i = reader.getPos();
            JSONSkipKit.skipStringArg(reader, val, true);
            value = new String(reader.getData(), i, reader.getPos() - i);
        }


        return new JSONBool(value);
    }

    public static JSONEntity readJSONNull(JSONReader reader, JSONConfig jsonConfig) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            JSONSkipKit.skipStringArg(reader, "null", true);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONNull.class);
        }
        String value;
        if (jsonConfig.isHasSkipped()) {
            value = getString(reader);
        } else {
            int i = reader.getPos();
            JSONSkipKit.skipBlank(reader);
            value = new String(reader.getData(), i, reader.getPos() - i);
        }

        return new JSONNull(value);
    }

    public static JSONEntity readJSONNumber(JSONReader reader, JSONConfig jsonConfig) {
        if (jsonConfig.isLazy()) {
            int pos = reader.getPos();
            boolean isDecimal = JSONSkipKit.skipNumber(reader);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, isDecimal ? JSONDouble.class : JSONLong.class);
        }
        String value;
        boolean isDecimal;
        if (jsonConfig.isHasSkipped()) {
            value = getString(reader);
            isDecimal = value.indexOf(Key.NUMBER_INTERVAL) != -1;
        } else {
            int i = reader.getPos();
            isDecimal = JSONSkipKit.skipNumber(reader);
            value = new String(reader.getData(), i, reader.getPos() - i);
        }

        if (isDecimal) {
            return new JSONDouble(value);
        }
        return new JSONLong(value);
    }

    public static JSONEntity readJSONString(JSONReader reader, JSONConfig config) {
        if (config.isLazy()) {
            int pos = reader.getPos() + 1;
            JSONSkipKit.skipString(reader);
            JSONReader readObject = new JSONReader(reader.getData(), pos, reader.getPos());
            return new JSONLazy(readObject, JSONString.class);
        }
        String value;
        if (config.isHasSkipped()) {
            value = getString(reader);
        } else {
            int i = reader.getPos() + 1;
            JSONSkipKit.skipString(reader);
            value = new String(reader.getData(), i, reader.getPos() - i);
        }

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

    private static String getString(JSONReader reader) {
        return new String(reader.getData(), reader.getPos(), reader.getEnd() - reader.getPos());
    }
}
