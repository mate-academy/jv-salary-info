package core.basesyntax;

public class EmployeeInfo {
    private String name;
    private int salary;

    public EmployeeInfo(String name) {
        this.name = name;
    }

    public void addSalary(int hours, int salaryPerHour) {
        salary += hours * salaryPerHour;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + salary;
    }
}
