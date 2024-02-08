package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int INDEX_HOURS = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        if (dateFrom == null || dateFrom.isEmpty() || dateTo == null || dateTo.isEmpty()
                || names.length == 0 || data.length == 0) {
            return "0";
        }
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] variables = datum.split(" ");
                LocalDate date = LocalDate.parse(variables[DATE_INDEX], FORMATTER);
                String nameEmployee = variables[NAME_INDEX];
                int hours = Integer.parseInt(variables[INDEX_HOURS]);
                int salaryPerHour = Integer.parseInt(variables[SALARY_INDEX]);
                if (!date.isAfter(toDate) && !date.isBefore(fromDate)
                        && nameEmployee.equals(name)) {
                    salary += (hours * salaryPerHour);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
