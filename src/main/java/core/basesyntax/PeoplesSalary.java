package core.basesyntax;

import java.time.LocalDate;

public class PeoplesSalary {
    private LocalDate workDay;
    private String name;
    private int numberOfHours;
    private int salaryForHours;

    public LocalDate getWorkDay() {
        return workDay;
    }

    public void setWorkDay(LocalDate workDay) {
        this.workDay = workDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getSalaryForHours() {
        return salaryForHours;
    }

    public void setSalaryForHours(int salaryForHours) {
        this.salaryForHours = salaryForHours;
    }
}
