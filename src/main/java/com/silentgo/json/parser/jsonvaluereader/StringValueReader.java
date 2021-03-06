package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import com.silentgo.json.model.JSONString;
import com.silentgo.json.parser.JSONReaderKit;
import com.silentgo.json.parser.JSONSkipKit;
import com.silentgo.json.parser.Reader;

/**
 * Project : json
 * Package : com.silentgo.json.parser.jsonvaluereader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class StringValueReader implements JSONValueReader<JSONString> {
    @Override
    public JSONEntity readValue(Reader reader, JSONConfig jsonConfig, JSONString outJsonObject, int depth) {
        boolean forceLazy = depth > jsonConfig.getMaxDepth();
        if (jsonConfig.isLazy() || forceLazy) {
            int pos = reader.pos + 1;
            JSONSkipKit.skipString(reader);
            return new JSONLazy(reader.expand(pos, reader.pos + 1), JSONString.class);
        }
        String value;
        if (jsonConfig.isHasSkipped()) {
            value = JSONReaderKit.getString(reader);
        } else {
            value = JSONReaderKit.readString(reader);
        }

        return new JSONString(value);
    }
}
