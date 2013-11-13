package io.github.cell303.rdndc.invoker;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface rdndc {
    public String value() default "";
}
