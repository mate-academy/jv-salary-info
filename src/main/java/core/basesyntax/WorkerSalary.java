package core.basesyntax;

import java.time.LocalDate;

public class WorkerSalary {
    private LocalDate date;
    private String name;
    private int workedHours;
    private int salary;
    private int rate;

    public WorkerSalary(LocalDate date, String name, int workedHours, int rate) {
        this.date = date;
        this.name = name;
        this.workedHours = workedHours;
        this.rate = rate;
        this.salary += rate * workedHours;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        SalaryParser salaryParser = new SalaryParser();
        return date;
    }

    public String getName() {
        return name;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public int getRate() {
        return rate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        salary = salary;
    }

}
