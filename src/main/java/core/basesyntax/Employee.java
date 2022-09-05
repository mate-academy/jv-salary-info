package core.basesyntax;

import java.time.LocalDate;

public class Employee {
    private LocalDate dayOfWorkStart;
    private String name;
    private int numberOfWorkingDays;
    private int workingHours;

    public LocalDate getDayOfWorkStart() {
        return dayOfWorkStart;
    }

    public void setDayOfWorkStart(LocalDate dayOfWorkStart) {
        this.dayOfWorkStart = dayOfWorkStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(int numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
}
