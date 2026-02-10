package core.basesyntax;
public class Employee {
    private String name;
    private int salary = 0;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            return;
        }
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void changeSalary(int salary) {
        if (salary == 0) {
            return;
        }
        this.salary += salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
