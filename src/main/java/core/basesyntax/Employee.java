package core.basesyntax;

public class Employee implements SalaryCalculator {
    private final String name;
    private int currentSalary;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void calculateSalaryPerDay(int workingHour, int incomePerHour) {
        currentSalary += workingHour * incomePerHour;
    }

    public String getSalaryInfo() {
        return name + " - " + currentSalary;
    }
}
