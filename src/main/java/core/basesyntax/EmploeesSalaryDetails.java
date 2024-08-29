package core.basesyntax;

import java.time.LocalDate;

public class EmploeesSalaryDetails {
    private String emploeeName;
    private LocalDate workingDay;
    private int salaryPerHour;
    private int hourCount;

    public EmploeesSalaryDetails() {

    }

    public EmploeesSalaryDetails(String emploeeName, LocalDate workingDay,
                                 int salaryPerHour, int hourCount) {
        this.emploeeName = emploeeName;
        this.workingDay = workingDay;
        this.salaryPerHour = salaryPerHour;
        this.hourCount = hourCount;
    }

    public String getEmploeeName() {
        return emploeeName;
    }

    public void setEmploeeName(String name) {
        this.emploeeName = name;
    }

    public LocalDate getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(LocalDate date) {
        this.workingDay = date;
    }

    public int getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public int getHourCount() {
        return hourCount;
    }

    public void setHourCount(int hourCount) {
        this.hourCount = hourCount;
    }
}
