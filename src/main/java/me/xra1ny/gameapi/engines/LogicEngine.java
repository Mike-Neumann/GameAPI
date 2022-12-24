package me.xra1ny.gameapi.engines;

import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.LogicHandler;
import org.jetbrains.annotations.NotNull;

public class LogicEngine extends Engine {
    public LogicEngine(@NotNull Game game) {
        super(game);
    }

    @Override
    public void onEnable() {
        getHandlers().add(new LogicHandler(1000 / getGame().getTargetTps(), this));
    }
}
