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

    String text;

    public StringReader(String input) {
        this.text = input;
        this.pos = -1;
        this.end = text.length();
    }

    public StringReader(String input, int pos, int end) {
        this.text = input;
        this.pos = pos;
        this.end = end;
    }

    @Override
    public char prev() {
        return text.charAt(--pos);
    }

    @Override
    public char next() {
        return text.charAt(++pos);
    }

    @Override
    public boolean hasNext() {
        return pos < end;
    }

    @Override
    public char peek() {
        return text.charAt(pos);
    }

    @Override
    public char peek(int i) {
        if (i > end) {
            return NULL;
        }
        return text.charAt(i);
    }

    @Override
    public char peekNext() {
        return peek(pos + 1);
    }

    @Override
    public String peekRange(int start, int length) {
        return text.substring(start, start + length);
    }

    @Override
    public Reader expand(int pos, int end) {
        return new StringReader(text, pos, end);
    }

}
