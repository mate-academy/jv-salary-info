package core.basesyntax;

public class Employee {
    private String name;
    private int salary = 0;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSalaryInfo() {
        StringBuilder sb = new StringBuilder();
        return sb.append(name).append(" - ").append(salary).toString();
    }

    public void addSalary(int salary) {
        this.salary += salary;
    }

}
