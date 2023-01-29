package me.xra1ny.gameapi.exceptions;

import org.jetbrains.annotations.NotNull;

public class NotAnnotatedException extends RuntimeException {
    public NotAnnotatedException(@NotNull Class<?> clazzToBeAnnotated, @NotNull Class<?> annotation) {
        super(clazzToBeAnnotated.getName() + " must be annotated with " + annotation.getName());
    }
}
