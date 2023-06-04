package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final String TITLE = "Report for period ";
    private static final String DATE_SEPARATOR = " - ";
    private static final String DATA_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder employeeSalaryReport = new StringBuilder();
        employeeSalaryReport.append(TITLE).append(dateFrom).append(DATE_SEPARATOR).append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, TIME_FORMATTER);

        for (String name : names) {
            int salary = 0;

            for (String employeeData : data) {
                String[] parts = employeeData.split(DATA_SEPARATOR);

                if (name.equals(parts[NAME_INDEX])) {
                    LocalDate date = LocalDate.parse(parts[DATE_INDEX], TIME_FORMATTER);
                    if (date.isEqual(localDateFrom)
                            || date.isAfter(localDateFrom)
                            && date.isEqual(localDateTo)
                            || date.isAfter(localDateFrom)
                            && date.isBefore(localDateTo)) {
                        salary += Integer.parseInt(parts[WORKING_HOURS_INDEX])
                                * Integer.parseInt(parts[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            employeeSalaryReport.append(System.lineSeparator())
                    .append(name)
                    .append(DATE_SEPARATOR)
                    .append(salary);
        }
        return employeeSalaryReport.toString();
    }
}

