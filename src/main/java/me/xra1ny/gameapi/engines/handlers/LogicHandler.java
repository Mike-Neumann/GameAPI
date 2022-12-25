package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.engines.Engine;
import me.xra1ny.gameapi.objects.GameObject;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class LogicHandler extends EngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private TPSHandler tpsHandler;
    @Getter
    @Setter
    private int countedTicks;

    public LogicHandler(int interval, Engine engine) {
        super(interval, engine);
    }

    @Override
    public void onEnable() {
        tpsHandler = new TPSHandler(getEngine(), this);
    }

    @Override
    public void tick() {
        countedTicks++;
        final GameScreen screen = getEngine().getGame().getCurrentScreen();
        for(GameObject gameObject : screen.getGameObjects()) {
            if(gameObject.allowTick()) {
                gameObject.logicTick(screen.getGame());
            }
        }
    }
}
