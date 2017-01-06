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

    private boolean hasSkipped = false;

    public JSONConfig(boolean hasSkipped) {
        this.hasSkipped = hasSkipped;
    }

    public boolean isHasSkipped() {
        return hasSkipped;
    }

    public void setHasSkipped(boolean hasSkipped) {
        this.hasSkipped = hasSkipped;
    }

    public boolean isLazy() {
        return lazy;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }
}
