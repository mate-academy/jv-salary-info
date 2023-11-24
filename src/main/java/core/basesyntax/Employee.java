package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name) {
        this.name = new String(name);
    }

    public void addSalary(int salaryByDay, int hour) {
        salary += salaryByDay * hour;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
