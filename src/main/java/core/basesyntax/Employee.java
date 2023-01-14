package core.basesyntax;

import java.util.ArrayList;

public class Employee {
    private final String name;
    private ArrayList<DailySalaryData> salaryData;

    public Employee(String name) {
        this.name = name;
        this.salaryData = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addToSalaryData(DailySalaryData data) {
        this.salaryData.add(data);
    }

    public ArrayList<DailySalaryData> getSalaryData() {
        return salaryData;
    }
}
