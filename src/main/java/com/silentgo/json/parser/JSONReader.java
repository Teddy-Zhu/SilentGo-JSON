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
    private byte[] data;
    private int pos;
    private int end;

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
        return pos + 1 <= end;
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

    public static byte getByteNull() {
        return BYTE_NULL;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

}
