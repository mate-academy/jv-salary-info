package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entry {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS_WORKED = 2;
    private static final int INDEX_OF_HOURLY_RATE = 3;
    private LocalDate date;
    private String name;
    private int hoursWorked;
    private int hourlyRate;

    public Entry(String[] entryData) {
        this.date = LocalDate.parse(entryData[INDEX_OF_DATE], FORMATTER);
        this.name = entryData[INDEX_OF_NAME];
        this.hoursWorked = Integer.parseInt(entryData[INDEX_OF_HOURS_WORKED]);
        this.hourlyRate = Integer.parseInt(entryData[INDEX_OF_HOURLY_RATE]);
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
