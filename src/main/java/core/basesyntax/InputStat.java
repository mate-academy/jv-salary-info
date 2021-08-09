package core.basesyntax;

import java.util.Date;

public class InputStat {
    private Date date;
    private String name;
    private int hours;
    private int rate;

    public InputStat(Date date, String name, int hours, int rate) {
        this.date = date;
        this.name = name;
        this.hours = hours;
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getRate() {
        return rate;
    }
}
