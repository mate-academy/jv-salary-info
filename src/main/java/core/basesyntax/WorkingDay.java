package core.basesyntax;

import java.time.LocalDate;

public class WorkingDay {
    private LocalDate workingDay;
    private String name;
    private int workingHours;
    private int income;

    public WorkingDay(LocalDate workingDay, String name, int workingHours, int income) {
        this.workingDay = workingDay;
        this.name = name;
        this.workingHours = workingHours;
        this.income = income;
    }

    public LocalDate getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(LocalDate workingDay) {
        this.workingDay = workingDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
}
