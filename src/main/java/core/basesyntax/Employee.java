package core.basesyntax;

public class Employee {
    private String name;
    private int salary = 0;

    public Employee(String name) {
        this.name = name;
    }

    public void calcSalary(int hours, int rate) {
        salary += hours * rate;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
