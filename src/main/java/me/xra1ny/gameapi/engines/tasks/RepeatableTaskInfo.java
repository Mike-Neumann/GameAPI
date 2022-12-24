package me.xra1ny.gameapi.engines.tasks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepeatableTaskInfo {
    /** Defines the Interval in which this Task should be performed */
    int interval();
}
