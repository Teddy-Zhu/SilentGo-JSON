package com.silentgo.json.annotation;

import java.lang.annotation.*;

/**
 * Project : json
 * Package : com.silentgo.json.annotation
 *
 * @author <a href="mailto:teddyzhu15@gmail.com" target="_blank">teddyzhu</a>
 *         <p>
 *         Created by teddyzhu on 2017/1/6.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface JSONConstructor {
}
