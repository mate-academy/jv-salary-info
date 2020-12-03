package core.basesyntax;

import java.time.LocalDate;

public class WorkingDay {

    private LocalDate workingDate;
    private int hoursWorked;
    private int hourIncome;

    public WorkingDay(LocalDate workingDate, int hoursWorked, int hourIncome) {
        this.workingDate = workingDate;
        this.hoursWorked = hoursWorked;
        this.hourIncome = hourIncome;
    }

    public LocalDate getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(LocalDate workingDate) {
        this.workingDate = workingDate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getHourIncome() {
        return hourIncome;
    }

    public void setHourIncome(int hourIncome) {
        this.hourIncome = hourIncome;
    }
}
