package core.basesyntax;

import java.util.ArrayList;

public class Employee {
    private final String name;
    private final ArrayList<DailySalaryData> salaryData;

    public Employee(String name) {
        this.name = name;
        this.salaryData = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addDailySalary(DailySalaryData data) {
        this.salaryData.add(data);
    }

    public ArrayList<DailySalaryData> getSalaryData() {
        return salaryData;
    }
}
