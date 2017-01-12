package com.silentgo.json.parser;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class ByteReader extends Reader {

    public static final char NULL = 0;
    public byte[] data;

    public ByteReader(byte[] data, int pos, int end) {
        this.data = data;
        this.pos = pos;
        this.end = end;
    }

    @Override
    public char next() {
        return (char) data[++pos];
    }

    @Override
    public boolean hasNext() {
        return pos < end;
    }

    @Override
    public char peek() {
        return (char) data[pos];
    }

    @Override
    public char peek(int i) {
        if (i > end) {
            return NULL;
        }
        return (char) data[i];
    }

    @Override
    public char peekNext() {
        return peek(pos + 1);
    }

    @Override
    public String peekRange(int start, int length) {
        return new String(data, start, length);
    }

    @Override
    public Reader expand(int pos, int end) {
        return new ByteReader(data, pos, end);
    }

}
