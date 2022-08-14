package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int START_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final int DAY_TO_PLUS = 1;
    private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String employee: names) {
            stringBuilder.append(employee)
                    .append(" - ")
                    .append(employeeSalaryChecker(employee, data, localDateFrom, localDateTo))
                    .append(System.lineSeparator());

        }
        return stringBuilder.toString().trim();
    }

    private int employeeSalaryChecker(
            String name, String[] data, LocalDate dateFrom, LocalDate dateTo) {
        int salary = 0;
        for (String line: data) {
            String[] arrayOfLines = line.split(" ");
            if (checkDatePeriod(
                    arrayOfLines[START_DATE_INDEX], dateFrom, dateTo)
                    && arrayOfLines[NAME_INDEX].equals(name)) {
                salary += Integer.parseInt(arrayOfLines[HOURS_OF_WORK_INDEX])
                     * Integer.parseInt(arrayOfLines[SALARY_PER_HOUR_INDEX]);
            }
        }
        return salary;
    }

    private boolean checkDatePeriod(
            String dataToCheck, LocalDate dateFrom, LocalDate dateTo) {
        dateTo = dateTo.plusDays(DAY_TO_PLUS);
        LocalDate localDateToCheck = LocalDate.parse(dataToCheck, DATE_TIME_FORMATTER);
        return localDateToCheck.isBefore(dateTo) && localDateToCheck.isAfter(dateFrom);
    }

}
