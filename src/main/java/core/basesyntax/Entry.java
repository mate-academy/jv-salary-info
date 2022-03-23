package core.basesyntax;

import java.time.LocalDate;

public class Entry {
    private LocalDate date;
    private String name;
    private int hoursWorked;
    private int hourlyRate;

    public Entry(LocalDate date, String name, int hoursWorked, int hourlyRate) {
        this.date = date;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getMoneyEarned() {
        return hoursWorked * hourlyRate;
    }
}
