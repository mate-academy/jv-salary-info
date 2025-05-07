package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkRecord {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private final LocalDate date;
    private final String employeeName;
    private final int hoursWorked;
    private final int hourlyRate;

    public WorkRecord(String record) {
        String[] parts = record.split(" ");
        this.date = LocalDate.parse(parts[0], DATE_FORMATTER);
        this.employeeName = parts[1];
        this.hoursWorked = Integer.parseInt(parts[2]);
        this.hourlyRate = Integer.parseInt(parts[3]);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int calculateEarnings() {
        return hoursWorked * hourlyRate;
    }
}
