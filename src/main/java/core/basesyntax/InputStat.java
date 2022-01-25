package core.basesyntax;

import java.time.LocalDate;

public class InputStat {
    private LocalDate date;
    private String name;
    private int hours;
    private int rate;

    public InputStat(LocalDate date, String name, int hours, int rate) {
        this.date = date;
        this.name = name;
        this.hours = hours;
        this.rate = rate;
    }

    public LocalDate getDate() {
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
