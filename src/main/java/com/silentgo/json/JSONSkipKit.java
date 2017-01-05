package com.silentgo.json;

import com.silentgo.json.common.Key;

import java.util.Stack;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public class JSONSkipKit {

    public static void skipObject(JSONReader reader) {
        Stack stack = new Stack();
        stack.push('{');
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                default:
                    continue;
                case Key.OBJECT_START: {
                    stack.push(b);
                    continue;
                }
                case Key.OBJECT_END: {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return;
                    }
                }
            }
        }
        throw JSONReport.error(reader, "can not found object closure }");
    }

    public static void skipArray(JSONReader reader) {
        Stack stack = new Stack();
        stack.push('[');
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                default:
                    continue;
                case Key.ARRAY_START: {
                    stack.push(b);
                    continue;
                }
                case Key.ARRAY_END: {
                    stack.pop();
                    if (stack.isEmpty()) {
                        return;
                    }
                }
            }
        }
        throw JSONReport.error(reader, "can not found array closure ]");
    }

    public static void skipString(JSONReader reader) {
        while (reader.hasNext()) {
            byte b = reader.next();
            switch (b) {
                default:
                    continue;
                case Key.STRING_SPLIT: {
                    if (reader.peek(reader.getPos() - 1) != '\\') {
                        return;
                    }
                }
            }
        }

        throw JSONReport.error(reader, "string end can not found");
    }

    public static boolean skipNumber(JSONReader reader) {
        boolean isDecimal = false;
        while (reader.hasNext()) {
            byte b = reader.next();
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
                    return isDecimal;
                }
                default: {
                    throw JSONReport.error(reader, "unknown num end");
                }
            }
        }
        return isDecimal;
    }

    public static void skipStringArg(JSONReader reader, String str, boolean ignoreCaseSensitive) {
        int i = 0;
        while (reader.hasNext() & i < str.length()) {
            byte b = reader.next();
            if (ignoreCaseSensitive) {
                if (Character.toLowerCase(b) != str.charAt(i)) {
                    throw JSONReport.error(reader, "unknown string");
                }
            } else {
                if (b != str.charAt(i)) {
                    throw JSONReport.error(reader, "unknown string");
                }
            }

        }
    }

    public static void skipBlank(JSONReader reader) {
        while (reader.hasNext()) {
            byte b = reader.next();
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
