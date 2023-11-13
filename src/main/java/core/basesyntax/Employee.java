package core.basesyntax;

public class Employee {
    private final String name;
    private int totalEarnings;

    public Employee(String name) {
        this.name = name;
        this.totalEarnings = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public void addEarnings(int amount) {
        this.totalEarnings += amount;
    }
}
