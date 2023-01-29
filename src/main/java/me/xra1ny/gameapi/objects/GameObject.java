package me.xra1ny.gameapi.objects;

import me.xra1ny.gameapi.CollisionSide;
import me.xra1ny.gameapi.engines.handlers.RenderResult;
import me.xra1ny.gameapi.engines.handlers.TickResult;
import me.xra1ny.gameapi.screens.GameScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public interface GameObject {
    double getX();
    void setX(double x);
    double getY();
    void setY(double y);
    double getXVelocity();
    void setXVelocity(double xVelocity);
    double getYVelocity();
    void setYVelocity(double yVelocity);
    double getWidth();
    double getHeight();
    double getRotation();
    void setRotation(double rotation);
    boolean isAllowTick();
    void setAllowTick(boolean allowTick);
    boolean isAllowRender();
    void setAllowRender(boolean allowRender);

    default boolean collidesWith(@NotNull GameObject GameObject, double tolerance) {
        return collidesWith(GameObject.getX(), GameObject.getY(), GameObject.getWidth(), GameObject.getHeight(), tolerance);
    }

    default boolean collidesWith(@NotNull Rectangle rectangle, double tolerance) {
        return collidesWith(rectangle.x, rectangle.y, rectangle.width, rectangle.height, tolerance);
    }

    default boolean collidesWith(@NotNull Point point, double tolerance) {
        return collidesWith(point.x, point.y, 1, 1, tolerance);
    }

    default boolean collidesWith(double x, double y, double width, double height, double tolerance) {
        return x+width+tolerance > getX() &&
                x < getX()+getWidth()+tolerance &&
                y < getY()+getHeight()+tolerance &&
                y+height+tolerance > getY();
    }

    @Nullable
    default CollisionSide getCollisionSide(@NotNull GameObject GameObject, double tolerance) {
        return getCollisionSide(GameObject.getX(), GameObject.getY(), GameObject.getWidth(), GameObject.getHeight(), tolerance);
    }

    @Nullable
    default CollisionSide getCollisionSide(@NotNull Rectangle rectangle, double tolerance) {
        return getCollisionSide(rectangle.x, rectangle.y, rectangle.width, rectangle.height, tolerance);
    }

    @Nullable
    default CollisionSide getCollisionSide(@NotNull Point point, double width, double height, double tolerance) {
        return getCollisionSide(point.x, point.y, width, height, tolerance);
    }

    @NotNull
    default CollisionSide getCollisionSide(double x, double y, double width, double height, double tolerance) {
        if(!collidesWith(x, y, width, height, tolerance)) {
            return CollisionSide.NONE;
        }

        double mtvX;
        double mtvY;

        if(getX()+getWidth()+tolerance > x+width+tolerance) {
            mtvX = (x+width+tolerance) - getX();
        }else {
            mtvX = x - (getX()+getWidth()+tolerance);
        }

        if(getY()+getHeight()+tolerance > y+height+tolerance) {
            mtvY = (y+height+tolerance) - getY();
        }else {
            mtvY = y - (getY()+getHeight()+tolerance);
        }

        if(Math.abs(mtvX) < Math.abs(mtvY)) {
            if(mtvX < 0) {
                return CollisionSide.RIGHT;
            }else {
                return CollisionSide.LEFT;
            }
        }else {
            if(mtvY < 0) {
                return CollisionSide.BOTTOM;
            }else {
                return CollisionSide.TOP;
            }
        }
    }

    /** Called by the default RenderingEngine when a render has been requested by its Screens Repeatable Task */
    RenderResult onRender(@NotNull GameScreen screen, @NotNull Graphics2D gtd);

    /** Called by the default LogicEngine when a tick has been requested by its LogicHandler Repeatable Task */
    default TickResult tick(@NotNull GameScreen gameScreen) {
        setX(getX()+getXVelocity());
        setY(getY()+getYVelocity());

        return onTick(gameScreen);
    }

    TickResult onTick(@NotNull GameScreen gameScreen);

    @Nullable
    Sprite getSprite();

    void setSprite(@NotNull Sprite sprite);
}
