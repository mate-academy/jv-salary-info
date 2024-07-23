package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String INFO_SEPARATOR = " - ";
    private static final String DATA_SEPARATOR = " ";
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_WORK_HOURS = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder employeeInfo = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(INFO_SEPARATOR)
                .append(dateTo)
                .append(getDateAndSalary(names, data, dateFrom, dateTo));
        return employeeInfo.toString();
    }

    private String getDateAndSalary(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDateWithFormatter(dateFrom);
        LocalDate endDate = parseDateWithFormatter(dateTo);
        StringBuilder dateAndSalary = new StringBuilder();
        for (String name : names) {
            int totalSalary = 0;
            dateAndSalary.append(System.lineSeparator())
                    .append(name)
                    .append(INFO_SEPARATOR);
            for (String currentData : data) {
                String[] dateSplit = currentData.split(DATA_SEPARATOR);
                LocalDate date = parseDateWithFormatter(dateSplit[INDEX_OF_DATA]);
                if ((date.equals(startDate) || date.isAfter(startDate))
                        && (date.equals(endDate) || date.isBefore(endDate))
                        && name.equals(dateSplit[INDEX_OF_NAME])) {
                    totalSalary += Integer.parseInt(dateSplit[INDEX_OF_WORK_HOURS])
                            * Integer.parseInt(dateSplit[INDEX_SALARY_PER_HOUR]);
                }
            }
            dateAndSalary.append(totalSalary);
        }
        return dateAndSalary.toString();
    }

    private LocalDate parseDateWithFormatter(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
