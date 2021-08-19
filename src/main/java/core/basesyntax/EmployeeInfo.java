package core.basesyntax;

public class EmployeeInfo {
    private String name;
    private int summarySalary;

    public EmployeeInfo(String name) {
        this.name = name;
        this.summarySalary = 0;
    }

    public void addSalary(int hours, int salaryPerHour) {
        this.summarySalary += hours * salaryPerHour;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + summarySalary;
    }
}
