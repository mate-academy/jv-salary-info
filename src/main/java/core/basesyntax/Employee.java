package core.basesyntax;

public class Employee {
    private final String name;
    private int salary = 0;

    public Employee(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary += salary;
    }

    public String getName() {
        return name;
    }
}
