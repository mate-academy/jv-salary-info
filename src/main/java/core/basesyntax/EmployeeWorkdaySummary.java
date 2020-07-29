package core.basesyntax;

import java.time.LocalDate;

public class EmployeeWorkdaySummary {
    private String personName;
    private LocalDate date;
    private int hours;
    private int hourRate;

    public EmployeeWorkdaySummary(String personName, LocalDate date, int hours, int salaryPerHour) {
        this.personName = personName;
        this.date = date;
        this.hours = hours;
        this.hourRate = salaryPerHour;
    }

    public String getPersonName() {
        return personName;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public int getHourRate() {
        return hourRate;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setHourRate(int salaryPerHour) {
        this.hourRate = salaryPerHour;
    }
}
