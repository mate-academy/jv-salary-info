package core.basesyntax;

public class Employee extends SalaryData {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
