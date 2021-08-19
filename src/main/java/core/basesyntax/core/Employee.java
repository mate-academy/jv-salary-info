package core.basesyntax.core;

public class Employee {
    private String name;
    private long cash;

    public Employee(String name, long cash) {
        this.name = name;
        this.cash = cash;
    }

    public Employee(String name) {
        this(name,0);
    }

    public String getName() {
        return name;
    }

    public long getCash() {
        return cash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public void addCash(long cashToAdd) {
        this.cash += cashToAdd;
    }

    @Override
    public String toString() {
        return name + " - " + cash;
    }
}
