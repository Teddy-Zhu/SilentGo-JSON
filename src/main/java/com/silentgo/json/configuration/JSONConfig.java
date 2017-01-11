package com.silentgo.json.configuration;

/**
 * Project : SilentGo
 * Package : com.silentgo.json.configuration
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/5.
 */
public class JSONConfig implements Cloneable {

    private boolean lazy = false;

    private boolean hasSkipped = false;

    private int maxDepth = 20;

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public JSONConfig() {
    }

    public JSONConfig(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public JSONConfig(boolean lazy, int maxDepth) {
        this.lazy = lazy;
        this.maxDepth = maxDepth;
    }

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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
