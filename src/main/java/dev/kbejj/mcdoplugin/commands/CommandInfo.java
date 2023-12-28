package dev.kbejj.mcdoplugin.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {

    String command();
    String permission();
    String[] aliases() default {};
    String description();
    String usage();
    boolean player() default false;
    int[] args() default {1};
}
