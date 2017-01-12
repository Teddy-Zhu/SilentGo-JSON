package com.silentgo.json.deserializer;

/**
 * Project : json
 * Package : com.silentgo.json.deserializer
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/11.
 */
public class ArrayDeserializer extends CollectionDeserializer {

    public ArrayDeserializer(Deserializer child) {
        super(child);
    }
}
