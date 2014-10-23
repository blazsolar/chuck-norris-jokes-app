package com.github.blazsolar.chuck.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Blaz Solar on 23/10/14.
 */
@Qualifier @Retention(RetentionPolicy.RUNTIME)
public @interface IsMockMode {
}
