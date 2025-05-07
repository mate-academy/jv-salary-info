package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addSalary(int salary) {
        this.salary += salary;
    }

    public int getSalary() {
        return salary;
    }
}
