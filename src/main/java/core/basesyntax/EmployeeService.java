package core.basesyntax;

public class EmployeeService {
    public static Employee getEmployeeByName(Employee[] employees, String name) {
        for (Employee employee: employees) {
            if (employee.getName().equals(name)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee with name " + name + " not found");
    }
}
