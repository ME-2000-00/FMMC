package net.me.fmmc.timer;

import java.util.ArrayList;
import java.util.List;

public class TimerHandeler {

    private List<Timer> timers = new ArrayList<>();



    public void tick() {
        if (!timers.isEmpty()) {
            for (Timer timer : timers) {
                if (timer.has_ended()) {
                    timers.remove(timer.ID);
                } else {
                    timer.tick();
                }
            }
        }
    }

    /// returns the ID of the new timer
    public int add_timer(int time) {

        Timer timer = new Timer();
        timer.set_time(time);
        timer.ID = timers.size() - 1;

        timers.add(timer);

        return timer.ID;
    }
}
