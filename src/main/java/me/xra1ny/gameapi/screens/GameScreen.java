package me.xra1ny.gameapi.screens;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.application.Screen;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.objects.GameObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Slf4j
public abstract class GameScreen extends Screen {
    @Getter(onMethod = @__(@NotNull))
    private final Game game;
    @Getter(onMethod = @__(@NotNull))
    private final List<GameObject> GameObjects = new ArrayList<>();

    public GameScreen(@NotNull Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        final Graphics2D gtd = (Graphics2D) g;
        boolean skip = false;
        try {
            for(GameObject gameObject : getGameObjects()) {
                if(skip) {
                    skip = false;
                    continue;
                }

                if(gameObject.isAllowRender()) {
                    final RenderResult renderResult = gameObject.onRender(this, gtd);

                    if(renderResult == RenderResult.ESCAPE) {
                        break;
                    }else if(renderResult == RenderResult.SKIP_NEXT) {
                        skip = true;
                    }
                }
            }
        }catch(ConcurrentModificationException ignored) {}
        renderTick(gtd);
    }

    /** Called when the default RenderingEngine requests a repaint on this GameScreen */
    public abstract void renderTick(@NotNull Graphics2D gtd);

    @Override
    public final void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        getGame().onKeyPress(e);
        onKeyPress(e);
    }
    public abstract void onKeyPress(@NotNull KeyEvent e);

    @Override
    public void keyReleased(KeyEvent e) {
        getGame().onKeyRelease(e);
        onKeyRelease(e);
    }
    public abstract void onKeyRelease(@NotNull KeyEvent e);

    @Override
    public final void mouseClick(@NotNull MouseEvent e) {}

    @Override
    public void mousePress(@NotNull MouseEvent e) {
        getGame().onMousePress(e);
        onMousePress(e);
    }
    public abstract void onMousePress(@NotNull MouseEvent e);

    @Override
    public void mouseRelease(@NotNull MouseEvent e) {
        getGame().onMouseRelease(e);
        onMouseRelease(e);
    }
    public abstract void onMouseRelease(@NotNull MouseEvent e);

    @Override
    public void mouseEnter(@NotNull MouseEvent e) {
        getGame().onMouseEnterComponent(e);
        onMouseEnterComponent(e);
    }
    public abstract void onMouseEnterComponent(@NotNull MouseEvent e);

    @Override
    public void mouseExit(@NotNull MouseEvent e) {
        getGame().onMouseExitComponent(e);
        onMouseExitComponent(e);
    }
    public abstract void onMouseExitComponent(@NotNull MouseEvent e);
}
