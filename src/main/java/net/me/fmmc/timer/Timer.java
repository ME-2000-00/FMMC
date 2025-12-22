package net.me.fmmc.timer;

public class Timer {
    public int ID = -1;
    private int counter = 0;
    private boolean has_started = false;

    public void tick() {
        if (counter > 0) {
            counter--;
        }
    }

    public void set_time(int v) {
        this.counter = v;
        has_started = true;
    }

    public boolean has_ended() {
        return has_started && this.counter < 1;
    }
}
