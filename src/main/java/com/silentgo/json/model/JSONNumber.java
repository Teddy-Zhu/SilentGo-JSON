package com.silentgo.json.model;

import com.silentgo.json.parser.JSONReader;

/**
 * Project : json
 * Package : com.silentgo.json.model
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public abstract class JSONNumber extends JSONEntity {
    public JSONNumber(JSONReader value) {
        super(value);
    }
}
