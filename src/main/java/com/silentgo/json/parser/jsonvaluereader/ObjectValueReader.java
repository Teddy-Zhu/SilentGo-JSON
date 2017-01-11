package com.silentgo.json.parser.jsonvaluereader;

import com.silentgo.json.common.Key;
import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;
import com.silentgo.json.model.JSONObject;
import com.silentgo.json.parser.JSONReader;
import com.silentgo.json.parser.JSONReaderKit;
import com.silentgo.json.parser.JSONSkipKit;
import com.silentgo.json.report.JSONReport;

/**
 * Project : json
 * Package : com.silentgo.json.parser.jsonvaluereader
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class ObjectValueReader implements JSONValueReader<JSONObject> {
    @Override
    public JSONEntity readValue(JSONReader reader, JSONConfig jsonConfig, JSONObject outJsonObject, int depth) {
        boolean forceLazy = depth > jsonConfig.getMaxDepth();
        int initialPos = reader.pos;

        if (jsonConfig.isLazy() || forceLazy) {
            JSONSkipKit.skipObject(reader);
            JSONReader readObject = new JSONReader(reader.data, initialPos, reader.pos);
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
                    int pos = reader.pos + 1;
                    JSONSkipKit.skipString(reader);
                    String name = new String(reader.data, pos, reader.pos - pos);

                    byte key = JSONReaderKit.nextWord(reader);
                    if (Key.VALUE_COL != key)
                        new JSONReport().report(reader, "can not found value");
                    JSONEntity jsonEntity = JSONReaderKit.getReader(JSONEntity.class).readValue(reader, jsonConfig, null, depth);
                    jsonObject.put(name, jsonEntity);
                    continue;
                }
                case Key.OBJECT_SPLIT: {
                    if (isFirst) new JSONReport().report(reader, "unexcepted , begin the object");
                    continue;
                }
                case Key.OBJECT_END: {
                    jsonObject.setString(new String(reader.data, initialPos, reader.pos - initialPos + 1));
                    return jsonObject;
                }
            }
        }
        new JSONReport().report(reader, "can not found object closure }");
        return null;
    }
}
