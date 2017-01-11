package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.configuration.JSONConfigExtra;
import com.silentgo.json.model.JSONBool;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
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
public class BoolValueReader implements JSONValueReader<JSONBool> {
    @Override
    public JSONEntity readValue(JSONReader reader, JSONConfig jsonConfig, JSONBool outJsonObject, int depth) {
        boolean forceLazy = depth > jsonConfig.getMaxDepth();
        String val = jsonConfig instanceof JSONConfigExtra ? ((JSONConfigExtra) jsonConfig).getExtra().toString() : "false";
        if (jsonConfig.isLazy() || forceLazy) {
            int pos = reader.pos;
            JSONSkipKit.skipStringArg(reader, val, true);
            JSONReader readObject = new JSONReader(reader.data, pos, reader.pos);
            return new JSONLazy(readObject, JSONBool.class);
        }

        String value;
        if (jsonConfig.isHasSkipped()) {
            value = JSONReaderKit.getString(reader);
        } else {
            int i = reader.pos;
            JSONSkipKit.skipStringArg(reader, val, true);
            value = new String(reader.data, i, reader.pos - i);
        }


        return new JSONBool(value);
    }
}
