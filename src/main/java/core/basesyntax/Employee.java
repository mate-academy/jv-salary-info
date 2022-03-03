package core.basesyntax;

import java.time.LocalDate;

/**
 * @version 1.0
 * @autor Denys.Shl
 * @created by 02/03/2022 - 20:11
 * @project salaryInfo
 */
public class Employee {
    private String name;
    private int workingHour;
    private int salary;
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(int workingHour) {
        this.workingHour = workingHour;
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
        return "Employee{" +
                "name='" + name + '\'' +
                ", workingHour=" + workingHour +
                ", salary=" + salary +
                ", date=" + date +
                '}';
    }
}