package com.silentgo.json;

import com.silentgo.json.exception.ParseException;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONReport {
    public static ParseException error(JSONReader jsonReader, String msg) {

        int pos = jsonReader.getPos() - 10;
        String errorData = new String(jsonReader.getData(), pos < 0 ? 0 : pos, 10);

        throw new ParseException("Json parse error : " + msg + " around \n" + errorData + " \ncol :" + jsonReader.getPos());
    }
}
