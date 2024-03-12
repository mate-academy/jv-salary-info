package core.basesyntax.model;

import java.time.LocalDate;

public class EmployeeWorkDayInfo {
    private String employeeName;
    private LocalDate date;
    private int incomePerHour;
    private int hours;

    public EmployeeWorkDayInfo(String employeeName, LocalDate date, int hours, int incomePerHour) {
        this.employeeName = employeeName;
        this.date = date;
        this.hours = hours;
        this.incomePerHour = incomePerHour;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getIncomePerHour() {
        return incomePerHour;
    }

    public int getHours() {
        return hours;
    }
}
