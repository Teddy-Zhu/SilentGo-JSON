package com.silentgo.json.stream;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Project : json
 * Package : com.silentgo.json.stream
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/24.
 */
public class JSONStream extends OutputStream {
    public byte[] buf = new byte[4096];
    public int count = 0;

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        int i = off;
        for (; ; ) {
            for (; i < off + len && count < buf.length; i++) {
                buf[count++] = b[i];
            }
            if (count == buf.length) {
                byte[] newByte = new byte[buf.length * 2];
                System.arraycopy(buf, 0, newByte, 0, buf.length);
                buf = newByte;
            } else {
                break;
            }
        }
    }

    @Override
    public void write(int b) throws IOException {
        if (count == buf.length) {
            byte[] newByte = new byte[buf.length * 2];
            System.arraycopy(buf, 0, newByte, 0, buf.length);
            buf = newByte;
        }
        buf[count++] = (byte) b;
    }

    @Override
    public String toString() {
        return new String(buf, 0, count);
    }

    public void clear() {
        count = 0;
    }
}
