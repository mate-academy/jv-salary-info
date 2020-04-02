package core.basesyntax;

public class Employee {
    private String name;
    private int totalSalary = 0;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getTotalSalary() {
        return totalSalary;
    }

    public void addToTotalSalary(int dailySalary) {
        totalSalary += dailySalary;
    }
}
