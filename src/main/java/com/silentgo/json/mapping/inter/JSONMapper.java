package com.silentgo.json.mapping.inter;

import java.util.Collection;
import java.util.List;

/**
 * Project : json
 * Package : com.silentgo.json.mapping
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public interface JSONMapper<C> {

    public <T> Collection<T> mapCollection(C json, Class<T> tClass, String name);

    public <T> Collection<T> mapCollection(C json, Class<T> tClass);

    public <T> T map(C json, Class<T> tClass, String name);

    public <T> T map(C json, T entity, String name);

}
