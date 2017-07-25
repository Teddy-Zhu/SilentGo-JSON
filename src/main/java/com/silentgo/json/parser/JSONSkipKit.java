package com.silentgo.json.parser;

import com.silentgo.json.common.Key;
import com.silentgo.json.report.JSONReport;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONSkipKit {

    public static void skipObject(Reader reader) {
        int i = 1;
        while (reader.hasNext()) {
            int b = reader.next();
            switch (b) {
                default:
                    continue;
                case Key.STRING_SPLIT: {
                    skipString(reader);
                    continue;
                }
                case Key.OBJECT_START: {
                    i++;
                    continue;
                }
                case Key.OBJECT_END: {
                    i--;
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
        new JSONReport().report(reader, "can not find object closure }");
    }

    public static void skipArray(Reader reader) {
        int i = 1;
        while (reader.hasNext()) {
            int b = reader.next();
            switch (b) {
                default:
                    continue;
                case Key.STRING_SPLIT: {
                    skipString(reader);
                    continue;
                }
                case Key.ARRAY_START: {
                    i++;
                    continue;
                }
                case Key.ARRAY_END: {
                    i--;
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
        new JSONReport().report(reader, "can not find array closure ]");
    }

    public static void skipString(Reader reader) {
        while (reader.hasNext()) {
            int b = reader.next();
            switch (b) {
                default:
                    continue;
                case Key.STRING_SPLIT: {
                    if (reader.peek(reader.pos - 1) != '\\') {
                        return;
                    }
                }
            }
        }
        new JSONReport().report(reader, "string end can not find");
    }

    public static boolean skipNumber(Reader reader) {
        boolean isDecimal = false;
        while (reader.hasNext()) {
            int b = reader.next();
            switch (b) {
                case Key.NUMBER_INTERVAL: {
                    isDecimal = true;
                }
                case Key.NUMBER_VAL_SIGN:
                case Key.NUMBER_VAL0:
                case Key.NUMBER_VAL1:
                case Key.NUMBER_VAL2:
                case Key.NUMBER_VAL3:
                case Key.NUMBER_VAL4:
                case Key.NUMBER_VAL5:
                case Key.NUMBER_VAL6:
                case Key.NUMBER_VAL7:
                case Key.NUMBER_VAL8:
                case Key.NUMBER_VAL9: {
                    continue;
                }
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                case Key.OBJECT_SPLIT:
                case Key.OBJECT_END:
                case Key.ARRAY_END: {
                    reader.prev();
                    return isDecimal;
                }
                default: {
                    new JSONReport().report(reader, "unknown num end");
                }
            }
        }
        return isDecimal;
    }

    public static void skipStringArg(Reader reader, String str, boolean ignoreCaseSensitive) {
        int i = 1;
        if (ignoreCaseSensitive) {
            while (reader.hasNext() && i < str.length()) {
                int b = reader.next();
                if (Character.toLowerCase(b) != str.charAt(i)) {
                    new JSONReport().report(reader, "unknown string");
                }
                i++;
            }
        } else {
            while (reader.hasNext() && i < str.length()) {
                int b = reader.next();
                if (b != str.charAt(i)) {
                    new JSONReport().report(reader, "unknown string");
                }
                i++;
            }
        }
    }


    public static void skipBlank(Reader reader) {
        while (reader.hasNext()) {
            int b = reader.next();
            switch (b) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                case Key.OBJECT_SPLIT:
                case Key.OBJECT_END:
                case Key.ARRAY_END: {
                    return;
                }
                default: {
                    continue;
                }
            }
        }
    }

}
