package core.basesyntax;

import java.time.LocalDate;

public class Entry {

    private final LocalDate date;
    private final String name;
    private final int hours;
    private final double rate;

    public Entry(LocalDate date, String name, int hours, double rate) {
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

    public double getRate() {
        return rate;
    }

    public double calculateSalary() {
        return hours * rate;
    }

    @Override
    public String toString() {
        return "Entry{" + "date=" + date
                + ", name='" + name + '\'' + ", hours=" + hours
                + ", rate=" + rate + '}';
    }
}

