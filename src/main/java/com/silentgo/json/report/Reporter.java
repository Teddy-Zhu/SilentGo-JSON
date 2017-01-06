package com.silentgo.json.report;


import com.silentgo.json.parser.JSONReader;

/**
 * Project : json
 * Package : com.silentgo.json.report
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/6.
 */
public interface Reporter {

    public void report(JSONReader reader, String msg);
}
