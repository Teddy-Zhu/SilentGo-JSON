package com.silentgo.json.model;

import com.silentgo.json.JSONReader;
import com.silentgo.json.JSONReaderKit;
import com.silentgo.json.common.JSONConstructor;

import java.lang.reflect.InvocationTargetException;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSONLazy extends JSONEntity {

    private JSONEntity value;
    private Class<? extends JSONEntity> type;
    private JSONReader reader;

    public JSONLazy(JSONReader reader, Class<? extends JSONEntity> type) {
        super(null);
        this.reader = reader;
        this.type = type;
    }

    public JSONEntity get() {
        if (this.value == null) {
            try {
                this.value = (JSONEntity) JSONConstructor.getConstructorMap().get(type).newInstance(reader);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return this.value;
    }

}
