package core.basesyntax;

import java.time.LocalDate;

public class WorkRecord {
    private LocalDate date;
    private String employeeName;
    private int hours;
    private int rate;

    public WorkRecord(LocalDate date, String employeeName, int hours, int rate) {
        this.date = date;
        this.employeeName = employeeName;
        this.hours = hours;
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getHours() {
        return hours;
    }

    public int getRate() {
        return rate;
    }
}
