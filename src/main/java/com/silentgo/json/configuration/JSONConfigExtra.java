package com.silentgo.json.configuration;

/**
 * Project : json
 * Package : com.silentgo.json.configuration
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/7.
 */
public class JSONConfigExtra extends JSONConfig {

    private Object extra;

    public JSONConfigExtra(boolean hasSkipped) {
        super(hasSkipped);
    }

    public JSONConfigExtra(JSONConfig config, Object extra) {
        setLazy(config.isLazy());
        setHasSkipped(config.isHasSkipped());
        this.extra = extra;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
