package com.silentgo.json.format.impl;

import com.silentgo.json.format.JSONFormatter;
import com.silentgo.utils.ConvertKit;
import com.silentgo.utils.inter.ITypeConvertor;

import java.util.Date;

/**
 * Project : json
 * Package : com.silentgo.json.format.impl
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/6.
 */
public class DateFormatter implements JSONFormatter<Date> {
    @Override
    public Date format(String value) {
        ITypeConvertor convertor = ConvertKit.getTypeConvert(String.class, Date.class);
        return (Date) convertor.convert(value);
    }
}
