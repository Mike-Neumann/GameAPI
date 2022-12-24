package me.xra1ny.gameapi.objects;

import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;

public abstract class Entity implements GameObject {
    private int x, y;
    private float xVelocity, yVelocity;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
    public float getXVelocity() {
        return xVelocity;
    }
    public void setXVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }
    public float getYVelocity() {
        return yVelocity;
    }
    public void setYVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void logicTick(@NotNull Game game) {
        x+=xVelocity;
        y+=yVelocity;
        tick(game);
    }

    public abstract void tick(@NotNull Game game);
}
