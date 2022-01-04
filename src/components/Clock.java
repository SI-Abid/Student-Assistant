package components;

import javax.swing.JLabel;

public class Clock implements Runnable{
    Thread clock;
    String time;
    JLabel clockLabel;
    public Clock(JLabel clockLabel) {
        clock = new Thread(this);
        clock.start();
        this.clockLabel = clockLabel;
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time = new java.util.Date().toString();
            time = time.substring(11, 19);
            clockLabel.setText(time);
        }
    }
    public String getTime() {
        return time;
    }
}
