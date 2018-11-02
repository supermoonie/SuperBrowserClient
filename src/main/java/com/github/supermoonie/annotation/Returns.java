package com.github.supermoonie.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author supermoonie
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface Returns {

    String value();
}
