package com.silentgo.json.stream;

import com.silentgo.json.exception.SerializerException;
import com.silentgo.utils.log.Log;
import com.silentgo.utils.log.LogFactory;

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
public class EscpaeKit {

    private static final Log LOGGER = LogFactory.get();
    private static final byte[] ITOA = new byte[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    private static final ThreadLocal<JSONStream> streamThreadLocal = ThreadLocal.withInitial(JSONStream::new);

    public static Object escpaeChar(char a) {
        switch (a) {
            case '"':
            case '\\':
            case '/':
            case '\b':
            case '\f':
            case '\n':
            case '\r':
            case '\t':
                return new char[]{'\\', a};
            default:
                return a;
        }
    }

    public static String escpaeString(String val) {
        JSONStream stream = streamThreadLocal.get();
        stream.clear();
        int i = 0;
        int valLen = val.length();
        for (; i < valLen; i++) {
            char c = val.charAt(i);
            if (c >= 128) {
                break;
            }
            switch (c) {
                case '"':
                case '\\':
                case '/':
                case '\b':
                case '\f':
                case '\n':
                case '\r':
                case '\t':
                    break;
                default:
                    stream.buf[stream.count++] = (byte) c;
                    continue;
            }
            break;
        }
        if (i == valLen) {
            return stream.toString();
        }
        try {
            writeStringSlowPath(stream, val, i, valLen);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new SerializerException(e);
        }
        return stream.toString();
    }

    private static void writeStringSlowPath(OutputStream stream, String val, int i, int valLen) throws IOException {
        for (; i < valLen; i++) {
            int c = val.charAt(i);
            if (c >= 128) {
                stream.write('\\');
                stream.write('u');
                byte b4 = (byte) (c & 0xf);
                byte b3 = (byte) (c >> 4 & 0xf);
                byte b2 = (byte) (c >> 8 & 0xf);
                byte b1 = (byte) (c >> 12 & 0xf);
                stream.write(ITOA[b1]);
                stream.write(ITOA[b2]);
                stream.write(ITOA[b3]);
                stream.write(ITOA[b4]);
            } else {
                switch (c) {
                    case '"':
                        stream.write('\\');
                        stream.write('"');
                        break;
                    case '\\':
                        stream.write('\\');
                        stream.write('\\');
                        break;
                    case '/':
                        stream.write('\\');
                        stream.write('/');
                        break;
                    case '\b':
                        stream.write('\\');
                        stream.write('b');
                        break;
                    case '\f':
                        stream.write('\\');
                        stream.write('f');
                        break;
                    case '\n':
                        stream.write('\\');
                        stream.write('n');
                        break;
                    case '\r':
                        stream.write('\\');
                        stream.write('r');
                        break;
                    case '\t':
                        stream.write('\\');
                        stream.write('t');
                        break;
                    case 'u':
                    default:
                        stream.write(c);
                }
            }
        }
    }

}
