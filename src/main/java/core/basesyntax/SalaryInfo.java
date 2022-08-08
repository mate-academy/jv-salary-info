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
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo + "\n");
        for (String employee: names) {
            stringBuilder.append(employee)
                    .append(" - ")
                    .append(employeeSalaryChecker(employee, data, dateFrom, dateTo))
                    .append(System.lineSeparator());

        }
        return stringBuilder.toString().trim();
    }

    private int employeeSalaryChecker(String name, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        for (String line: data) {
            String[] arrayOfLines = line.split(" ");
            if (checkDatePeriod(
                    arrayOfLines[START_DATE_INDEX], localDateFrom, localDateTo)
                    && arrayOfLines[NAME_INDEX].equals(name)) {
                salary += Integer.parseInt(arrayOfLines[HOURS_OF_WORK_INDEX])
                     * Integer.parseInt(arrayOfLines[SALARY_PER_HOUR_INDEX]);
            }
        }
        return salary;
    }

    private static boolean checkDatePeriod(
            String dataToCheck, LocalDate dateFrom, LocalDate dateTo) {
        dateTo = dateTo.plusDays(DAY_TO_PLUS);
        LocalDate localDateToCheck = LocalDate.parse(dataToCheck, dateTimeFormatter);
        return localDateToCheck.isBefore(dateTo) && localDateToCheck.isAfter(dateFrom);
    }

}
