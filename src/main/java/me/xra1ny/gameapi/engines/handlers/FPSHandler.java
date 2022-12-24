package me.xra1ny.gameapi.engines.handlers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.gameapi.engines.Engine;
import me.xra1ny.gameapi.engines.tasks.RepeatableTaskInfo;
import org.jetbrains.annotations.NotNull;

@Slf4j
@RepeatableTaskInfo(interval = 1000)
public class FPSHandler extends EngineHandler {
    @Getter(onMethod = @__(@NotNull))
    private final RenderHandler renderHandler;

    public FPSHandler(@NotNull Engine engine, @NotNull RenderHandler renderHandler) {
        super(engine);

        this.renderHandler = renderHandler;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void tick() {
        if(renderHandler != null) {
            getEngine().getGame().setCurrentFps(renderHandler.getCountedFrames());
            renderHandler.setCountedFrames(0);

            getEngine().getGame().setTitle("FPS: " + getEngine().getGame().getCurrentFps());
        }
    }
}
