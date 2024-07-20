package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];

        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }

        for (String dataRow: data) {
            String[] dataRowArray = dataRow.split(" ");
            if (DateService.dateInRange(dataRowArray[0], dateFrom, dateTo)) {
                Employee currentEmployee = EmployeeService.getEmployeeByName(
                        employees, dataRowArray[1]
                );
                currentEmployee.addBalance(
                        Integer.parseInt(dataRowArray[2]) * Integer.parseInt(dataRowArray[3])
                );
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (Employee employee: employees) {
            result.append("\n")
                    .append(employee.getName())
                    .append(" - ")
                    .append(employee.getBalance());
        }

        return result.toString();
    }
}
