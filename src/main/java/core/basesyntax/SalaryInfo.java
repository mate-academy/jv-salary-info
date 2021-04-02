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

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] employees = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(employees[0], DATE_FORMAT);
                String name = employees[1];
                int hours = Integer.parseInt(employees[2]);
                int employeesSalary = Integer.parseInt(employees[3]);
                if (name.equals(names[i]) && (localDate.isAfter(begin) || localDate.isEqual(begin))
                        && (localDate.isBefore(end) || localDate.isEqual(end))) {
                    salary = salary + hours * employeesSalary;
                }
            }
            salaryInfo.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}


