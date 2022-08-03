package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int START_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;
    private static final int DAY_TO_PLUS = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo + "\n");
        for (String employee: names) {
            stringBuilder.append(
                    employee + " - "
                            + employeeSalaryChecker(employee, data, dateFrom, dateTo) + "\n");

        }
        return stringBuilder.toString().trim();
    }

    private int employeeSalaryChecker(String name, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        for (String line: data) {
            String[] arrayOfLines = line.split(" ");
            if (dateChecker(
                    arrayOfLines[START_DATE_INDEX], dateFrom, dateTo)
                    && arrayOfLines[NAME_INDEX].equals(name)) {
                salary += Integer.parseInt(arrayOfLines[HOURS_OF_WORK_INDEX])
                     * Integer.parseInt(arrayOfLines[SALARY_PER_HOUR_INDEX]);
            }
        }
        return salary;
    }

    private static boolean dateChecker(String dataToCheck, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter).plusDays(DAY_TO_PLUS);
        LocalDate localDateToCheck = LocalDate.parse(dataToCheck, dateTimeFormatter);
        return localDateToCheck.isBefore(localDateTo) && localDateToCheck.isAfter(localDateFrom);
    }

}
