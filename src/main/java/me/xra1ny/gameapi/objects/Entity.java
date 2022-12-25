package me.xra1ny.gameapi.objects;

import me.xra1ny.gameapi.Game;
import org.jetbrains.annotations.NotNull;

public abstract class Entity implements GameObject {
    private int x, y;
    private float xVelocity, yVelocity;
    private Sprite sprite;
    private double rotation;
    private boolean allowTick = true, allowRender = true;

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
    public boolean allowTick() {
        return allowTick;
    }

    @Override
    public void setAllowTick(boolean allowTick) {
        this.allowTick = allowTick;
    }

    @Override
    public boolean allowRender() {
        return allowRender;
    }

    @Override
    public void setAllowRender(boolean allowRender) {
        this.allowRender = allowRender;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @NotNull
    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void setSprite(@NotNull Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void logicTick(@NotNull Game game) {
        x+=xVelocity;
        y+=yVelocity;
        tick(game);
    }

    public abstract void tick(@NotNull Game game);
}
