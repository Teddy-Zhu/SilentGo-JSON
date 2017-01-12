package com.silentgo.json.parser;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/4.
 */
public abstract class Reader {
    public int pos;
    public int end;

    public abstract char next();

    public abstract boolean hasNext();

    public abstract char peek();

    public abstract char peek(int position);

    public abstract char peekNext();

    public abstract String peekRange(int start, int length);

    public abstract Reader expand(int pos, int end);
}
