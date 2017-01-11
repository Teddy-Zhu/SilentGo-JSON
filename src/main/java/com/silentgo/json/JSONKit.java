package com.silentgo.json;

import com.silentgo.json.model.JSONEntity;
import com.silentgo.json.model.JSONLazy;

/**
 * Project : json
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class JSONKit {

    public static boolean isTargetJSON(Object source, Class<? extends JSONEntity> target) {
        if (source instanceof JSONLazy) {
            return ((JSONLazy) source).getType().equals(target);
        } else {
            return target.isAssignableFrom(source.getClass());
        }
    }

}
