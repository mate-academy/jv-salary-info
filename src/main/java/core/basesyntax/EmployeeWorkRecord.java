package core.basesyntax;

import java.time.LocalDate;

public class EmployeeWorkRecord {
    private final LocalDate date;
    private final String name;
    private final int hoursWorked;
    private final int incomePerHour;

    public EmployeeWorkRecord(
            LocalDate date,
            String name,
            int hoursWorked,
            int incomePerHour) {

        this.date = date;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.incomePerHour = incomePerHour;
    }

    public int calculateSalary() {
        return hoursWorked * incomePerHour;
    }

    public boolean belongsTo(String employeeName) {
        return name.equals(employeeName);
    }

    public boolean wasWorkingBetween(LocalDate from, LocalDate to) {
        return !date.isBefore(from) && !date.isAfter(to);
    }
}
