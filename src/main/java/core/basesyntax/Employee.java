package core.basesyntax;

import java.time.LocalDate;

public class Employee {
    private String name;
    private LocalDate date;
    private int hoursWorked;
    private int hourlyRate;

    public Employee(String name, LocalDate date, int hoursWorked, int hourlyRate) {
        this.name = name;
        this.date = date;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
}
