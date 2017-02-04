package com.silentgo.json.parser;

/**
 * Project : json
 * Package : com.silentgo.json.parser
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/12.
 */
public class StringReader extends ByteReader {

    public StringReader(String input) {
        super(input.getBytes(), -1, input.getBytes().length);
    }

    public StringReader(String input, int pos, int end) {
        super(input.getBytes(), pos, end);
    }

}
