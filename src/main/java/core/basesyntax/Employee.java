package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;

public class Employee {

    private String name;
    private WorkingDay workingDay;
    private ArrayList<WorkingDay> data = new ArrayList<>();

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, WorkingDay workingDay) {
        this.name = name;
        this.workingDay = workingDay;
    }

    public void addWorkingDay(WorkingDay workingDay) {
        getData().add(workingDay);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkingDay getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(WorkingDay workingDay) {
        this.workingDay = workingDay;
    }

    public ArrayList<WorkingDay> getData() {
        return data;
    }

    public ArrayList<WorkingDay> getData(LocalDate dateFrom, LocalDate dateTo) {
        ArrayList<WorkingDay> filtered = new ArrayList<>();
        for (WorkingDay day : getData()) {
            if (day.getWorkingDate().isAfter(dateFrom) && day.getWorkingDate().isBefore(dateTo)
                    || day.getWorkingDate().isEqual(dateFrom)
                    || day.getWorkingDate().isEqual(dateTo)) {
                filtered.add(day);
            }
        }
        return filtered;
    }

    public int getTotalSalary() {
        int salary = 0;
        for (WorkingDay day : getData()) {
            salary += day.getHourIncome() * day.getHoursWorked();
        }
        return salary;
    }

    public int getSalary(LocalDate dateFrom, LocalDate dateTo) {
        int salary = 0;
        for (WorkingDay day : getData(dateFrom, dateTo)) {
            salary += day.getHourIncome() * day.getHoursWorked();
        }
        return salary;
    }
}
