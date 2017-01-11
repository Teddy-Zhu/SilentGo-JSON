package com.silentgo.json.parser;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONReader implements Reader {

    public static final byte BYTE_NULL = 0;
    public byte[] data;
    public int pos;
    public int end;

    public JSONReader(byte[] data, int pos, int end) {
        this.data = data;
        this.pos = pos;
        this.end = end;
    }

    @Override
    public byte next() {
        return data[++pos];
    }

    @Override
    public boolean hasNext() {
        return pos < end;
    }

    @Override
    public byte peek() {
        return data[pos];
    }

    @Override
    public byte peek(int i) {
        if (i > end) {
            return BYTE_NULL;
        }
        return data[i];
    }

    @Override
    public byte peekNext() {
        return peek(pos + 1);
    }

}
