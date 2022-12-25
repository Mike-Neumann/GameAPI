package me.xra1ny.gameapi.engines;

import lombok.Getter;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.RenderHandler;
import org.jetbrains.annotations.NotNull;

public class RenderingEngine extends Engine {
    @Getter(onMethod = @__(@NotNull))
    private RenderHandler renderHandler;

    public RenderingEngine(@NotNull Game game) {
        super(game);
    }

    @Override
    public void onEnable() {
        renderHandler = new RenderHandler(1000 / getGame().getTargetFps(), this);
        renderHandler.enable();
    }
}
