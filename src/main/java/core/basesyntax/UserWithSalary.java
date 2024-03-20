package core.basesyntax;

public class UserWithSalary {
    private final String userName;
    private int salaryAmount;

    public UserWithSalary(String name) {
        this.userName = name;
    }

    public String getUserName() {
        return userName;
    }

    public int getSalaryAmount() {
        return salaryAmount;
    }

    public void addMoneyToSalary(int amount) {
        salaryAmount += amount;
    }
}
