package me.xra1ny.gameapi.objects;

import lombok.Getter;
import me.xra1ny.gameapi.engines.tasks.RepeatableTask;
import org.jetbrains.annotations.NotNull;

public class AnimatedSprite extends RepeatableTask {
    @Getter(onMethod = @__(@NotNull))
    private final SpriteSheet spriteSheet;
    @Getter(onMethod = @__(@NotNull))
    private Sprite currentSprite;

    public AnimatedSprite(@NotNull SpriteSheet spriteSheet, int interval) {
        super(interval);
        this.spriteSheet = spriteSheet;
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void tick() {
        if(currentSprite == null) {
            if(spriteSheet.getSprites().size() > 0) {
                currentSprite = spriteSheet.getSprites().get(0);
            }else {
                currentSprite = new Sprite("");
            }
        }else {
            int index = spriteSheet.getSprites().indexOf(currentSprite)+1;

            if(index >= spriteSheet.getSprites().size()) {
                index = 0;
            }

            currentSprite = spriteSheet.getSprites().get(index);
        }
    }
}
