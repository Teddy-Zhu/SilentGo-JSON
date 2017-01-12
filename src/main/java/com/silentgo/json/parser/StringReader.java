package com.silentgo.json.parser;

/**
 * Project : json
 * Package : com.silentgo.json.parser
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/12.
 */
public class StringReader extends Reader {
    public String input;

    public StringReader(String input) {
        this.input = input;
        this.pos = -1;
        this.end = input.length() - 1;
    }

    public StringReader(String input, int pos, int end) {
        this.input = input;
        this.pos = pos;
        this.end = end;
    }

    @Override
    public char next() {
        return input.charAt(++pos);
    }

    @Override
    public boolean hasNext() {
        return pos < end;
    }

    @Override
    public char peek() {
        return input.charAt(pos);
    }

    @Override
    public char peek(int position) {
        return input.charAt(position);
    }

    @Override
    public char peekNext() {
        return input.charAt(pos + 1);
    }

    @Override
    public String peekRange(int start, int len) {
        return input.substring(start, start + len);
    }

    @Override
    public Reader expand(int pos, int end) {
        return new StringReader(input, pos, end);
    }


}
