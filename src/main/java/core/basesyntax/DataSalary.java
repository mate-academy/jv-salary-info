package core.basesyntax;

import java.time.LocalDate;

public class DataSalary {
    private LocalDate date;
    private String name;
    private int hoursQuantity;
    private int salaryPreHour;

    public DataSalary() {
    }

    public DataSalary(LocalDate date, String name, int hoursQuantity, int salaryPreHour) {
        this.date = date;
        this.name = name;
        this.hoursQuantity = hoursQuantity;
        this.salaryPreHour = salaryPreHour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursQuantity() {
        return hoursQuantity;
    }

    public void setHoursQuantity(int hoursQuantity) {
        this.hoursQuantity = hoursQuantity;
    }

    public int getSalaryPreHour() {
        return salaryPreHour;
    }

    public void setSalaryPreHour(int salaryPreHour) {
        this.salaryPreHour = salaryPreHour;
    }
}
