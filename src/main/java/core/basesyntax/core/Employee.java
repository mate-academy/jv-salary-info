package core.basesyntax.core;

import java.util.Objects;

public class Employee {
    private String name;
    private long cash;

    public Employee(String name, long cash) {
        this.name = name;
        this.cash = cash;
    }

    public Employee(String name) {
        this(name, 0);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cash);
    }

    @Override
    public String toString() {
        return name + " - " + cash;
    }
}
