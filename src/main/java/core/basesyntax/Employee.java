package core.basesyntax;

public class Employee {
    private final String name;
    private int salary;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - " + salary + System.lineSeparator();
    }

}
