package me.xra1ny.gameapi.objects;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public abstract class Entity implements GameObject {
    @Getter
    @Setter
    private double x, y;

    @Getter
    @Setter
    private double xVelocity, yVelocity;

    @Getter(onMethod = @__(@NotNull))
    @Setter(onParam = @__(@NotNull))
    private Sprite sprite;

    @Getter
    @Setter
    private double rotation;

    @Getter
    @Setter
    private boolean allowTick = true, allowRender = true;

    @Getter
    @Setter
    private int ticksToSkip;
}
