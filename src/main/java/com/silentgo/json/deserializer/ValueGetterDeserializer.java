package com.silentgo.json.deserializer;

import com.silentgo.json.mapping.getter.ValueGetter;
import com.silentgo.json.model.JSONEntity;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ValueGetterDeserializer implements Deserializer {
    private ValueGetter valueGetter;

    public ValueGetterDeserializer(ValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    public ValueGetter getValueGetter() {
        return valueGetter;
    }

    public void setValueGetter(ValueGetter valueGetter) {
        this.valueGetter = valueGetter;
    }

    @Override
    public Object getObject(JSONEntity entity, Object key) {
        return valueGetter.getObject(entity);
    }
}
