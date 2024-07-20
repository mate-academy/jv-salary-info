package core.basesyntax;

public class Employee {
    private String name;
    private int balance;

    public Employee(String name) {
        this.name = name;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

}
