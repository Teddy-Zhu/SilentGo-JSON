package com.silentgo.json.stream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Project : json
 * Package : com.silentgo.json.stream
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/25.
 */
public class JSONCharStream extends OutputStream {
    public char[] buf = new char[256];
    public int count = 0;

    @Override
    public void write(int b) throws IOException {
        if (count == buf.length) {
            char[] newByte = new char[buf.length * 2];
            System.arraycopy(buf, 0, newByte, 0, buf.length);
            buf = newByte;
        }
        buf[count++] = (char) b;
    }

    @Override
    public String toString() {
        return new String(buf, 0, count);
    }

    public void clear() {
        count = 0;
    }
}
