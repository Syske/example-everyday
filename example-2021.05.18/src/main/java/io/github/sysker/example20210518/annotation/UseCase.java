package io.github.sysker.example20210518.annotation;

import java.lang.annotation.*;

/**
 * userCase
 *
 * @author sysker
 * @version 1.0
 * @date 2021-05-19 7:45
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();
    public String description() default "no description";
}
