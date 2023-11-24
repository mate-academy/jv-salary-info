package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String EMPTY_SEPARATOR = " ";
    private static final String START_REPORT_TEXT = "Report for period ";
    private static final String SEPARATOR = " - ";
    private static final int DATA_DATE = 0;
    private static final int DATA_NAME = 1;
    private static final int DATA_HOURS_PER_DAY = 2;
    private static final int DATA_SALARY = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append(START_REPORT_TEXT).append(dateFrom).append(SEPARATOR).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String records : data) {
                String[] splittedData = records.split(EMPTY_SEPARATOR);
                String dateFromData = splittedData[DATA_DATE];
                String nameFromData = splittedData[DATA_NAME];
                String hourPerDayFromData = splittedData[DATA_HOURS_PER_DAY];
                String salaryFromData = splittedData[DATA_SALARY];
                if (nameFromData.equals(name)) {
                    if (isValidDate(dateFrom, dateTo, dateFromData)) {
                        salary += Integer.parseInt(hourPerDayFromData)
                                * Integer.parseInt(salaryFromData);
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(SEPARATOR).append(salary);
        }
        return report.toString();
    }

    private boolean isValidDate(String dateFromString, String dateToString, String dateString) {
        LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
        LocalDate dateTo = LocalDate.parse(dateToString, formatter);
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date.isAfter(dateFrom) && date.isBefore(dateTo) || date.isEqual(dateTo);
    }
}
