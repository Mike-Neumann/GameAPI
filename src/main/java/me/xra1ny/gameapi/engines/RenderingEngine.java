package me.xra1ny.gameapi.engines;

import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.RenderHandler;
import org.jetbrains.annotations.NotNull;

public class RenderingEngine extends Engine {
    public RenderingEngine(@NotNull Game game) {
        super(game);
    }

    @Override
    public void onEnable() {
        getHandlers().add(new RenderHandler(1000 / getGame().getTargetFps(), this));
    }
}
