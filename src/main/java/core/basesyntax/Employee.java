package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee() {
        salary = 0;
    }

    public Employee(String name) {
        this();
        this.name = name;
    }

    public void addSalary(int value) {
        this.salary += value;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
