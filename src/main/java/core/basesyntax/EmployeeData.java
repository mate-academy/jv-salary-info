package core.basesyntax;

import java.time.LocalDate;

public class EmployeeData {
    private String name;
    private int workingHours;
    private int salary;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkingHour() {
        return workingHours;
    }

    public void setWorkingHour(int workingHours) {
        this.workingHours = workingHours;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employee{"
               + "name='" + name + '\''
               + ", workingHour=" + workingHours
               + ", salary=" + salary
               + ", date=" + date
               + '}';
    }
}
