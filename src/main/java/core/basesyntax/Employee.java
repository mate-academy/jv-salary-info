package core.basesyntax;

import java.util.ArrayList;

public class Employee {

    private String name;
    private WorkingDay workingDay;
    private ArrayList<WorkingDay> data;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, WorkingDay workingDay) {
        this.name = name;
        this.workingDay = workingDay;
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
}
