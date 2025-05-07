package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name) {
        this.name = name;
        this.salary = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
