package core.basesyntax;

public class Employee {
    private String name;
    private int salary;

    public Employee(String name) {
        this.name = new String(name);
        salary = 0;

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
