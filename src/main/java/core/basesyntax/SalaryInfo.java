package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dayFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dayTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder builderSalary = new StringBuilder();
        builderSalary.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        for (String name : names) {
            int employeeSalary = 0;
            for (String table : data) {
                String[] employeesData = table.split(" ");
                LocalDate salaryDay = LocalDate.parse(employeesData[0], DATE_TIME_FORMATTER);
                if (name.equals(employeesData[1])
                        && (salaryDay.isBefore(dayTo) || salaryDay.isEqual(dayTo))
                        && (salaryDay.isAfter(dayFrom) || salaryDay.isEqual(dayFrom))) {
                    employeeSalary += Integer.parseInt(employeesData[2])
                            * Integer.parseInt(employeesData[3]);
                }
            }
            builderSalary.append(name).append(" - ").append(employeeSalary).append("\n");
        }
        return builderSalary.substring(0, builderSalary.toString().length() - 1);
    }
}
