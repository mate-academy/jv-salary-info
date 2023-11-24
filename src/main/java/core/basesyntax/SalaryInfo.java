package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_FOR_DATE = 0;
    private static final int INDEX_FOR_NAME = 1;
    private static final int INDEX_FOR_HOURS_OF_WORK = 2;
    private static final int INDEX_FOR_SALARY_PER_HOUR = 3;
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom,
                dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo,
                dateTimeFormatter);
        int[] totalSalary = new int[names.length];
        for (String salary : data) {
            String[] info = salary.split(DELIMITER);
            LocalDate date = LocalDate.parse(info[INDEX_FOR_DATE],
                    dateTimeFormatter);
            if (isDateInRange(date, localDateFrom, localDateTo)) {
                countTotalSalary(names, totalSalary, info);
            }
        }
        return getReport(names, totalSalary, dateFrom, dateTo);
    }

    private boolean isDateInRange(LocalDate date, LocalDate localDateFrom, LocalDate localDateTo) {
        return (date.isAfter(localDateFrom) || date.isEqual(localDateFrom))
                && (date.isBefore(localDateTo) || date.isEqual(localDateTo));
    }

    private void countTotalSalary(String[] names, int[] totalSalary, String[] info) {
        int hoursOfWork = Integer.parseInt(info[INDEX_FOR_HOURS_OF_WORK]);
        int salaryPerHour = Integer.parseInt(info[INDEX_FOR_SALARY_PER_HOUR]);
        String name = info[INDEX_FOR_NAME];
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                totalSalary[i] += hoursOfWork * salaryPerHour;
                break;
            }
        }
    }

    private String getReport(String[] names, int[] totalSalary, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ").append(totalSalary[i]);
        }
        return result.toString();
    }
}
