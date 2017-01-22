package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.JSON;
import com.silentgo.json.common.Key;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.configuration.JSONConfigExtra;
import com.silentgo.json.model.*;
import com.silentgo.json.parser.ByteReader;
import com.silentgo.json.parser.JSONReaderKit;
import com.silentgo.json.parser.JSONSkipKit;
import com.silentgo.json.parser.Reader;
import com.silentgo.json.report.JSONReport;

/**
 * Project : json
 * Package : com.silentgo.json.parser.jsonvaluereader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class ArrayValueReader implements JSONValueReader<JSONArray> {
    @Override
    public JSONEntity readValue(Reader reader, JSONConfig jsonConfig, JSONArray outJsonObject, int depth) {
        boolean forceLazy = depth > jsonConfig.getMaxDepth();
        int initialPos = reader.pos;

        if (jsonConfig.isLazy() || forceLazy) {
            JSONSkipKit.skipArray(reader);
            Reader readObject = reader.expand(initialPos, reader.pos);
            return new JSONLazy(readObject, JSONArray.class);
        }


        JSONArray jsonArray = outJsonObject == null ? new JSONArray() : outJsonObject;
        boolean isFirst = true;
        while (reader.hasNext()) {
            char b = reader.next();
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
                        jsonArray.push(JSON.Null);
                    }
                    isFirst = false;
                    continue;
                }
                case Key.OBJECT_START: {
                    isFirst = false;
                    jsonArray.push(JSONReaderKit.Object.readValue(reader, jsonConfig, null, depth + 1));
                    continue;
                }
                case Key.ARRAY_START: {
                    isFirst = false;
                    jsonArray.push(JSONReaderKit.Array.readValue(reader, jsonConfig, null, depth + 1));
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
                    jsonArray.push(JSONReaderKit.Number.readValue(reader, jsonConfig, null, depth + 1));
                    isFirst = false;
                    continue;
                }
                case Key.STRING_SPLIT: {
                    jsonArray.push(JSONReaderKit.String.readValue(reader, jsonConfig, null, depth + 1));
                    isFirst = false;
                    continue;
                }
                case Key.ARRAY_END: {
                    jsonArray.setString(reader.peekRange(initialPos, reader.pos - initialPos + 1));
                    return jsonArray;
                }
                case Key.BOOL_FALSE: {
                    jsonArray.push(JSONReaderKit.Bool.readValue(reader, new JSONConfigExtra(jsonConfig, "false"), null, depth + 1));
                    isFirst = false;
                    continue;
                }
                case Key.BOOL_TRUE: {
                    jsonArray.push(JSONReaderKit.Bool.readValue(reader, new JSONConfigExtra(jsonConfig, "true"), null, depth + 1));
                    isFirst = false;
                    continue;
                }
            }
        }
        new JSONReport().report(reader, "can not find array closure ]");
        return null;
    }
}
