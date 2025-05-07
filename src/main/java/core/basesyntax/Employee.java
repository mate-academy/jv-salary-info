package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name) {
        this.name = name;
        salary = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary += salary;
    }
}
