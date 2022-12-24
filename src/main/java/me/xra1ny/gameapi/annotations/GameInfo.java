package me.xra1ny.gameapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GameInfo {
    /** Defines the Rate in which Frames are rendered each Second */
    int fps();

    /** Defines the Rate in which Logic Ticks are performed each Second */
    int tps();
}
