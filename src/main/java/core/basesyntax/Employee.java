package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name) {
        this.name = name;
        this.salary = 0;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void addSalary(int salary) {
        this.salary += salary;
    }
}
