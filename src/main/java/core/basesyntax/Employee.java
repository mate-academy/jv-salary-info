package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSalary(int add) {
        salary += add;
    }

    @Override
    public String toString() {
        return "\n" + name + " - "
                 + salary;
    }
}
