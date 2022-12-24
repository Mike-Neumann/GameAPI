package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.engines.Engine;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class RenderHandler extends EngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private FPSHandler fpsHandler;
    @Getter
    @Setter
    private int countedFrames = 0;

    public RenderHandler(int interval, Engine engine) {
        super(interval, engine);
    }


    @Override
    public void onEnable() {
        fpsHandler = new FPSHandler(getEngine(), this);
    }

    @Override
    public void tick() {
        countedFrames++;
        getEngine().getGame().getCurrentScreen().repaint();
    }
}
