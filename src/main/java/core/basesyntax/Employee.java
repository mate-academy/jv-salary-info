package core.basesyntax;

public class Employee {
    private int earnedMoney;
    private final String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getEarnedMoney() {
        return earnedMoney;
    }

    public void addEarnedMoney(int money) {
        this.earnedMoney += money;
    }
}
