package me.xra1ny.gameapi.engines.tasks;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public abstract class RepeatableTask {
    private Timer timer = new Timer();
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

        initialise();
    }

    public RepeatableTask(int interval) {
        this.interval = interval;

        initialise();
    }

    private void initialise() {
        log.info("Initialising RepeatableTask with Interval: " + interval);

        if(timer == null) {
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0L, interval);
    }

    public abstract void tick();
}
