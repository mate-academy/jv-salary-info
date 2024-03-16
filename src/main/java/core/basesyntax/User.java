package core.basesyntax;

public class User {
    private final String name;
    private int salaryAmount = 0;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSalaryAmount() {
        return salaryAmount;
    }

    public void addMoneyToSalary(int amount) {
        salaryAmount += amount;
    }
}
