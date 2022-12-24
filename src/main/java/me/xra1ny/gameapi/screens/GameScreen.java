package me.xra1ny.gameapi.screens;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.xra1ny.application.Screen;
import me.xra1ny.gameapi.Game;
import me.xra1ny.gameapi.objects.GameObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class GameScreen extends Screen {
    @Getter(onMethod = @__(@NotNull))
    private final Game game;
    @Getter(onMethod = @__(@NotNull))
    private final List<GameObject> gameObjects = new ArrayList<>();

    public GameScreen(@NotNull Game game) {
        this.game = game;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        render((Graphics2D) g);
    }

    public void render(@NotNull Graphics2D gtd) {
        renderTick(gtd);
        for(GameObject gameObject : gameObjects) {
            gameObject.renderTick(getGame(), gtd);
        }
    }

    /** Called when the default RenderingEngine requests a repaint on this GameScreen */
    public abstract void renderTick(@NotNull Graphics2D gtd);
}
