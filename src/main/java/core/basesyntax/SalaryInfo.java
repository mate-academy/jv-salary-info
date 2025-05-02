package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String newLine = System.lineSeparator();
        StringBuilder salaryInfo = new StringBuilder(String.format("Report for period %s - %s",
                dateFrom, dateTo));
        for (String name : names) {
            int employeeSalary = 0;
            for (String employeeData : data) {
                String[] employee = employeeData.split(" ");
                LocalDate employeeDate = LocalDate.parse(employee[0], formatter);
                LocalDate startDate = LocalDate.parse(dateFrom, formatter);
                LocalDate endDate = LocalDate.parse(dateTo, formatter);
                if (!employeeDate.isBefore(startDate) && !employeeDate.isAfter(endDate)
                        && employee[1].equals(name)) {
                    employeeSalary += Integer.parseInt(employee[2]) * Integer.parseInt(employee[3]);
                }
            }
            salaryInfo.append(newLine).append(name).append(" - ").append(employeeSalary);
        }
        return salaryInfo.toString();
    }
}
