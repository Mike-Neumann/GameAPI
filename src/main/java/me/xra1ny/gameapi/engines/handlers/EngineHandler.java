package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import me.xra1ny.gameapi.engines.Engine;
import me.xra1ny.gameapi.engines.tasks.RepeatableTask;
import org.jetbrains.annotations.NotNull;

public abstract class EngineHandler extends RepeatableTask {
    @Getter(onMethod = @__(@NotNull))
    private final Engine engine;

    public EngineHandler(@NotNull Engine engine) {
        this.engine = engine;

        onEnable();
    }

    public EngineHandler(int interval, Engine engine) {
        super(interval);
        this.engine = engine;

        onEnable();
    }

    public abstract void onEnable();
}
