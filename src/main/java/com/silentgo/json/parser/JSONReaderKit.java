package com.silentgo.json.parser;

import com.silentgo.json.model.*;
import com.silentgo.json.parser.jsonvaluereader.*;
import com.silentgo.json.report.JSONReport;
import com.silentgo.json.stream.JSONCharStream;
import com.silentgo.utils.log.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Character.*;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONReaderKit {

    final static int[] digits = new int[256];

    static {
        for (int i = 0; i < digits.length; i++) {
            digits[i] = -1;
        }
        for (int i = '0'; i <= '9'; ++i) {
            digits[i] = (i - '0');
        }
        for (int i = 'a'; i <= 'f'; ++i) {
            digits[i] = ((i - 'a') + 10);
        }
        for (int i = 'A'; i <= 'F'; ++i) {
            digits[i] = ((i - 'A') + 10);
        }
    }

    public static final JSONValueReader Number = new NumberValueReader();
    public static final JSONValueReader Bool = new BoolValueReader();
    public static final JSONValueReader Entity = new CommonValueReader();
    public static final JSONValueReader Null = new NullValueReader();
    public static final JSONValueReader String = new StringValueReader();
    public static final JSONValueReader Object = new ObjectValueReader();
    public static final JSONValueReader Array = new ArrayValueReader();

    private static final com.silentgo.utils.log.Log LOGGER = LogFactory.get();

    private static final ThreadLocal<JSONCharStream> jsonStream = ThreadLocal.withInitial(JSONCharStream::new);

    private static final Map<Class<? extends JSONEntity>, JSONValueReader> map = new HashMap<Class<? extends JSONEntity>, JSONValueReader>() {
        {
            put(JSONDouble.class, Number);
            put(JSONLong.class, Number);
            put(JSONBool.class, Bool);
            put(JSONEntity.class, Entity);
            put(JSONNull.class, Null);
            put(JSONString.class, String);
            put(JSONObject.class, Object);
            put(JSONArray.class, Array);
        }
    };

    public static JSONValueReader get(Class<? extends JSONEntity> clz) {
        return map.get(clz);
    }

    public static int nextWord(Reader reader) {

        while (reader.hasNext()) {
            int b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    continue;
                default:
                    return b;
            }
        }
        return ByteReader.NULL;
    }

    public static int nextToken(Reader reader) {
        return reader.hasNext() ? reader.next() : 0;
    }

    public static String getString(Reader reader) {
        return reader.peekRange(reader.pos, reader.end - reader.pos + 1);
    }

    //from jsonstream
    public static String readString(Reader reader) {
        JSONCharStream stream = jsonStream.get();
        stream.clear();
        try {
            while (reader.hasNext()) {
                int a = reader.next();
                if (a >= 0) {
                    switch (a) {
                        case '\\': {
                            int b2 = nextToken(reader);
                            switch (b2) {
                                case '"':
                                    stream.write('"');
                                    break;
                                case '\\':
                                    stream.write('\\');
                                    break;
                                case '/':
                                    stream.write('/');
                                    break;
                                case 'b':
                                    stream.write('\b');
                                    break;
                                case 'f':
                                    stream.write('\f');
                                    break;
                                case 'n':
                                    stream.write('\n');
                                    break;
                                case 'r':
                                    stream.write('\r');
                                    break;
                                case 't':
                                    stream.write('\t');
                                    break;
                                case 'u':
                                    stream.write(readU4(reader));
                                    break;
                                default:
                                    JSONReport.me().report(reader, "unexpected escape char: " + b2);
                            }
                            break;
                        }
                        case '"': {
                            return stream.toString();
                        }
                        default:
                            stream.write(a);
                    }
                } else if ((a >> 5) == -2 && (a & 0x1e) != 0) {
                    // 2 bytes, 11 bits: 110xxxxx 10xxxxxx
                    int b2 = nextToken(reader);
                    stream.write((char) (((a << 6) ^ b2)
                            ^
                            (((byte) 0xC0 << 6) ^
                                    ((byte) 0x80))));
                } else if ((a >> 4) == -2) {
                    // 3 bytes, 16 bits: 1110xxxx 10xxxxxx 10xxxxxx
                    int b2 = nextToken(reader);
                    int b3 = nextToken(reader);
                    char c = (char)
                            ((a << 12) ^
                                    (b2 << 6) ^
                                    (b3 ^
                                            (((byte) 0xE0 << 12) ^
                                                    ((byte) 0x80 << 6) ^
                                                    ((byte) 0x80))));
                    stream.write(c);
                } else if ((a >> 3) == -2) {
                    // 4 bytes, 21 bits: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
                    int b2 = nextToken(reader);
                    int b3 = nextToken(reader);
                    int b4 = nextToken(reader);
                    int uc = ((a << 18) ^
                            (b2 << 12) ^
                            (b3 << 6) ^
                            (b4 ^
                                    (((byte) 0xF0 << 18) ^
                                            ((byte) 0x80 << 12) ^
                                            ((byte) 0x80 << 6) ^
                                            ((byte) 0x80))));
                    stream.write(highSurrogate(uc));
                    stream.write(lowSurrogate(uc));
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }

        JSONReport.me().report(reader, "end of the string '\"' not found");
        return null;
    }

    private static char highSurrogate(int codePoint) {
        return (char) ((codePoint >>> 10)
                + (MIN_HIGH_SURROGATE - (MIN_SUPPLEMENTARY_CODE_POINT >>> 10)));
    }

    private static char lowSurrogate(int codePoint) {
        return (char) ((codePoint & 0x3ff) + MIN_LOW_SURROGATE);
    }

    public static char readU4(Reader reader) throws IOException {
        int v = digits[nextToken(reader)];
        if (v == -1) {
            JSONReport.me().report(reader, "bad unicode");
        }
        char b = (char) v;
        v = digits[nextToken(reader)];
        if (v == -1) {
            JSONReport.me().report(reader, "bad unicode");
        }
        b = (char) (b << 4);
        b += v;
        v = digits[nextToken(reader)];
        if (v == -1) {
            JSONReport.me().report(reader, "bad unicode");
        }
        b = (char) (b << 4);
        b += v;
        v = digits[nextToken(reader)];
        if (v == -1) {
            JSONReport.me().report(reader, "bad unicode");
        }
        b = (char) (b << 4);
        b += v;
        return b;
    }
}
