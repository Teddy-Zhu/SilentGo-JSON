package com.silentgo.json.report;

import com.silentgo.json.exception.ParseException;
import com.silentgo.json.parser.JSONReader;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONReport implements Reporter {

    @Override
    public void report(JSONReader reader, String msg) {
        int pos = reader.getPos() - 10;
        String errorData = new String(reader.getData(), pos < 0 ? 0 : pos, 10);
        throw new ParseException("Json parse error : " + msg + " around \n" + errorData + " \ncol :" + reader.getPos());
    }
}
