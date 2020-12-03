package core.basesyntax;

public class Person {
    private String name;
    private int salary;

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
        this.salary = salary;
    }

    public void addSalary(int additionalSalary) {
        salary += additionalSalary;
    }

    @Override
    public String toString() {
        return "\n" + name + " - " + salary;
    }
}
