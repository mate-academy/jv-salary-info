package core.basesyntax;

public class PersonTotalSalary {
    private static final int DEFAULT_SALARY = 0;
    private String name;
    private int salary;

    public PersonTotalSalary(String name) {
        this.name = name;
        this.salary = DEFAULT_SALARY;
    }

    public void incrementSalary(int increment) {
        this.salary += increment;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
