package me.xra1ny.gameapi.engines;

import lombok.Getter;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.LogicHandler;
import org.jetbrains.annotations.NotNull;

public class LogicEngine extends Engine {
    @Getter(onMethod = @__(@NotNull))
    private LogicHandler logicHandler;

    public LogicEngine(@NotNull Game game) {
        super(game);
    }

    @Override
    public void onEnable() {
        logicHandler = new LogicHandler(1000 / getGame().getTargetTps(), this);
        logicHandler.enable();
    }
}
