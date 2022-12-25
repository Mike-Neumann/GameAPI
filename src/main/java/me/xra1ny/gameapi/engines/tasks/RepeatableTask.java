package me.xra1ny.gameapi.engines.tasks;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public abstract class RepeatableTask {
    private Timer timer;
    @Getter
    private final int interval;

    public RepeatableTask() {
        final RepeatableTaskInfo info = getClass().getDeclaredAnnotation(RepeatableTaskInfo.class);

        if(info == null) {
            log.info("No RepeatableTaskInfo Annotation provided! Using default Settings...");

            interval = 1000;
        }else {
            interval = info.interval();
        }
    }

    public RepeatableTask(int interval) {
        this.interval = interval;
    }

    public void enable() {
        if(timer == null) {
            timer = new Timer();
            log.info("Initialising RepeatableTask " + this + " + with Interval " + interval);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    tick();
                }
            }, 0L, interval);
        }else {
            log.error("This RepeatableTask is already running! " + this);
        }

        onEnable();
    }

    public abstract void onEnable();

    public void kill() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public abstract void tick();
}
