package ru.heisenbug.jupiter;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(EnvAnnotationProcessor.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Env {

    Browser browser();

    boolean remote() default true;

    enum Browser {
        chrome, firefox
    }
}
