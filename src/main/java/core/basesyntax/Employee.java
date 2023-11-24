package core.basesyntax;

public class Employee {
    private final String name;
    private int income;

    public Employee(String name) {
        this.name = name;
        this.income = 0;
    }

    public String getName() {
        return name;
    }

    public int getIncome() {
        return income;
    }

    public void addIncome(int incomePerHour, int hours) {
        income += incomePerHour * hours;
    }
}
