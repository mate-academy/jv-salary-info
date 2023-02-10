package core.basesyntax;

public class Employee {
    private String name = new String();
    private int salary;

    public void add_info(String names, int salaryByDay, int hour) {
        if (name.equals(names)) {
            this.salary += salaryByDay * hour;
        } else {
            name = names;
            this.salary = salaryByDay * hour;
        }
    }

    public void add_info(String names) {
        if (name.equals(names)) {
            this.salary += 0;
        } else {
            name = names;
            this.salary = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
}
