package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entry {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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

    public int getHoursWorked() {
        return hoursWorked;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int getMoneyEarned() {
        return hoursWorked * hourlyRate;
    }
}
