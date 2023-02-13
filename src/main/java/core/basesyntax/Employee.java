package core.basesyntax;

public class Employee implements SalaryCalculator {
    public static final int STARTING_SALARY = 0;
    private final String name;
    private int currentSalary;

    public Employee(String name) {
        this.name = name;
        this.currentSalary = STARTING_SALARY;
    }

    public String getName() {
        return name;
    }

    @Override
    public void calculateSalaryPerDay(int workingHour, int incomePerHour) {
        currentSalary += workingHour * incomePerHour;
    }

    public String getSalaryInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name)
                .append(" - ")
                .append(currentSalary);
        return stringBuilder.toString();
    }
}
