package com.silentgo.json.report;

import com.silentgo.json.exception.ParseException;
import com.silentgo.json.parser.Reader;

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
    public void report(Reader reader, String msg) {
        int pos = reader.pos - 10;
        throw new ParseException("Json parse error : " + msg + " around \n" + reader.peekRange(pos > 0 ? pos : 0, (reader.pos + 5) < reader.end ? (reader.pos + 5) : reader.end) + " \ncol :" + reader.pos);
    }
}
