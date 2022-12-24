package me.xra1ny.gameapi.objects;

import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface GameObject {
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);
    int getWidth();
    int getHeight();

    default boolean collidesWith(@NotNull GameObject gameObject) {
        return collidesWith(gameObject.getX(), gameObject.getY(), gameObject.getWidth(), gameObject.getHeight());
    }

    default boolean collidesWith(@NotNull Rectangle rectangle) {
        return collidesWith(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    default boolean collidesWith(int x, int y, int width, int height) {
        return x+width >= getX() && x <= getX()+getWidth() &&
                y+height >= getY() && y <= getY()+getHeight();

    }

    /** Called by the default RenderingEngine when a render has been requested by its Screens Repeatable Task */
    void renderTick(@NotNull Game game, @NotNull Graphics2D gtd);

    /** Called by the default LogicEngine when a tick has been requested by its LogicHandler Repeatable Task */
    void logicTick(@NotNull Game game);
}
