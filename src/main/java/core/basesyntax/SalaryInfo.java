package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE = " ";
    private static final String DASH = " - ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        LocalDate forDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate forDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder resultSalary = new StringBuilder("Report for period "
                + dateFrom + DASH + dateTo);
        for (String name : names) {
            for (String dataRow : data) {
                String[] splitDateInfo = dataRow.split(SPACE);
                String entryDate = splitDateInfo[DATA_INDEX];
                String entryName = splitDateInfo[NAME_INDEX];
                int hoursWorked = Integer.parseInt(splitDateInfo[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(splitDateInfo[SALARY_INDEX]);
                LocalDate entryLocalDate = LocalDate.parse(entryDate, DATE_TIME_FORMATTER);
                if (entryName.equals(name)
                        && entryLocalDate.isAfter(forDateFrom.minusDays(1))
                        && entryLocalDate.isBefore(forDateTo.plusDays(1))) {
                    salary += hourlyRate * hoursWorked;
                }
            }
            resultSalary.append(LINE_SEPARATOR).append(name).append(DASH).append(salary);
            salary = 0;
        }
        return resultSalary.toString();
    }
}
