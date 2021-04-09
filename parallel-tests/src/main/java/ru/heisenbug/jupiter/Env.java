package ru.heisenbug.jupiter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Env {

    Browser browser();

    boolean remote() default true;

    enum Browser {
        chrome, firefox
    }
}
