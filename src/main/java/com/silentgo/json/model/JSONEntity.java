package com.silentgo.json.model;

import com.silentgo.json.parser.JSONReader;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public abstract class JSONEntity {

    public JSONEntity(JSONReader value) {
    }

    public abstract Object get();

}
