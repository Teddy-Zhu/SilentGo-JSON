package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.common.Key;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.*;
import com.silentgo.json.parser.JSONReader;
import com.silentgo.json.parser.JSONReaderKit;
import com.silentgo.json.parser.JSONSkipKit;

/**
 * Project : json
 * Package : com.silentgo.json.parser.jsonvaluereader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class NumberValueReader implements JSONValueReader<JSONNumber> {

    @Override
    public JSONEntity readValue(JSONReader reader, JSONConfig jsonConfig, JSONNumber outJsonObject, int depth) {
        boolean forceLazy = depth > jsonConfig.getMaxDepth();
        if (jsonConfig.isLazy() || forceLazy) {
            int pos = reader.pos;
            boolean isDecimal = JSONSkipKit.skipNumber(reader);
            JSONReader readObject = new JSONReader(reader.data, pos, reader.pos);
            return new JSONLazy(readObject, isDecimal ? JSONDouble.class : JSONLong.class);
        }
        String value;
        boolean isDecimal;
        if (jsonConfig.isHasSkipped()) {
            value = JSONReaderKit.getString(reader);
            isDecimal = value.indexOf(Key.NUMBER_INTERVAL) != -1;
        } else {
            int i = reader.pos;
            isDecimal = JSONSkipKit.skipNumber(reader);
            value = new String(reader.data, i, reader.pos - i);
        }

        if (isDecimal) {
            return new JSONDouble(value);
        }
        return new JSONLong(value);
    }
}
