package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate begin = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate end = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String s : names) {
            int salary = 0;
            for (String datum : data) {
                String[] employees = datum.split(" ");
                LocalDate localDate = LocalDate.parse(employees[0], DATE_FORMAT);
                String name = employees[1];
                int hours = Integer.parseInt(employees[2]);
                int employeesSalary = Integer.parseInt(employees[3]);
                if (name.equals(s) && (localDate.isAfter(begin) || localDate.isEqual(begin))
                        && (localDate.isBefore(end) || localDate.isEqual(end))) {
                    salary += hours * employeesSalary;
                }
            }
            salaryInfo.append("\n").append(s).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}