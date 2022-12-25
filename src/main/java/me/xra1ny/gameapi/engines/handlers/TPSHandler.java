package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import me.xra1ny.gameapi.engines.Engine;
import me.xra1ny.gameapi.engines.tasks.RepeatableTaskInfo;
import org.jetbrains.annotations.NotNull;

@RepeatableTaskInfo(interval = 1000)
public class TPSHandler extends EngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private final LogicHandler logicHandler;

    public TPSHandler(@NotNull Engine engine, @NotNull LogicHandler logicHandler) {
        super(engine);

        this.logicHandler = logicHandler;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void tick() {
        if(logicHandler != null) {
            getEngine().getGame().setCurrentTps(logicHandler.getCountedTicks());
            logicHandler.setCountedTicks(0);
        }
    }
}
