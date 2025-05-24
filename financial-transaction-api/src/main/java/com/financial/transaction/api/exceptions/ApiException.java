package com.financial.transaction.api.exceptions;

import com.financial.transaction.api.enums.Status;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * controller exception annotation
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface ApiException {

    Status value();
}
