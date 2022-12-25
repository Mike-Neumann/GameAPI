package me.xra1ny.gameapi.engines;

import lombok.Getter;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.EngineHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Engine {
    @Getter(onMethod = @__(@NotNull))
    private final Game game;
    @Getter(onMethod = @__(@NotNull))
    private final List<EngineHandler> handlers = new ArrayList<>();

    public Engine(@NotNull Game game) {
        this.game = game;
    }

    /**
     * Enabled this Engine
     */
    public void enable() {
        onEnable();
    }

    /** Defines all Actions to perform when construction of this Engine Object has finished */
    public abstract void onEnable();
}
