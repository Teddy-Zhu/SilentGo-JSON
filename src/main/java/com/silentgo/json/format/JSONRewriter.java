package com.silentgo.json.format;

/**
 * Project : json
 * Package : com.silentgo.json.format
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/6.
 */
public interface JSONRewriter<T> {
    public Object rewrite(Object object);
}
