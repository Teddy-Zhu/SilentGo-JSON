package com.silentgo.json.configuration;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.configuration
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSONConfig {

    private boolean lazy = false;

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }
}
