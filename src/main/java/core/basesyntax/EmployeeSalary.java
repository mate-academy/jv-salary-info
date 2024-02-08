package core.basesyntax;

public class EmployeeSalary {
    private String name;
    private int salary;

    public EmployeeSalary(String name) {
        this.name = name;
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
        this.salary = salary;
    }

    public void addSalary(int salary) {
        this.salary += salary;
    }
}
