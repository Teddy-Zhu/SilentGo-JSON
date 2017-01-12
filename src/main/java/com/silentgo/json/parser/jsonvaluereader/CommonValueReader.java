package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.common.Key;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.configuration.JSONConfigExtra;
import com.silentgo.json.model.*;
import com.silentgo.json.parser.JSONReaderKit;
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
public class CommonValueReader implements JSONValueReader<JSONEntity> {
    @Override
    public JSONEntity readValue(Reader reader, JSONConfig jsonConfig, JSONEntity outJsonObject, int depth) {

        int nextDepth = depth + 1;
        while (reader.hasNext()) {
            char b = reader.next();
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
                    return JSONReaderKit.Number.readValue(reader, jsonConfig, outJsonObject, nextDepth);
                }
                case Key.STRING_SPLIT: {
                    //string

                    return JSONReaderKit.String.readValue(reader, jsonConfig, outJsonObject, nextDepth);
                }
                case Key.OBJECT_START: {
                    //object {}
                    return JSONReaderKit.Object.readValue(reader, jsonConfig, outJsonObject, nextDepth);
                }
                case Key.ARRAY_START: {
                    //array []
                    return JSONReaderKit.Array.readValue(reader, jsonConfig, outJsonObject, nextDepth);
                }
                case Key.NULL: {
                    //null
                    return JSONReaderKit.Null.readValue(reader, jsonConfig, null, nextDepth);
                }
                case Key.BOOL_FALSE: {
                    return JSONReaderKit.Bool.readValue(reader, new JSONConfigExtra(jsonConfig, "false"), null, nextDepth);
                }
                case Key.BOOL_TRUE: {
                    return JSONReaderKit.Bool.readValue(reader, new JSONConfigExtra(jsonConfig, "true"), null, nextDepth);
                }
                default:
                    new JSONReport().report(reader, "can not handle this value");
            }
        }
        new JSONReport().report(reader, "unknown format string");
        return null;
    }
}
