package com.silentgo.json;

import com.silentgo.json.configuration.JSONConfig;
import com.silentgo.json.mapping.JSONEntityMapper;
import com.silentgo.json.mapping.inter.JSONMapper;

/**
 * Project : SilentGo
 * Package : com.silentgo.json
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSON {

    public static final JSONConfig config = new JSONConfig();

    public static final JSONConfig oneDepthConfig = new JSONConfig(1);
    public static final JSONMapper mapper = new JSONEntityMapper();

}
