package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.engines.Engine;
import me.xra1ny.gameapi.objects.GameObject;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

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
        fpsHandler.enable();
    }

    @Override
    public void tick() {
        countedFrames++;
        final GameScreen screen = getEngine().getGame().getCurrentScreen();
        screen.repaint();
    }
}
