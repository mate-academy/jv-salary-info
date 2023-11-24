package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String SEPARATOR = System.lineSeparator();
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (String name : names) {
            builder.append(SEPARATOR).append(name).append(" - ");
            int totalSalary = 0;
            for (String curretData : data) {
                String[] splitedData = curretData.split(" ");
                String workingHours = splitedData[INDEX_OF_HOURS];
                String hourSalary = splitedData[INDEX_OF_SALARY_PER_HOUR];
                String nameFromData = splitedData[INDEX_OF_NAME];
                String dateFromData = splitedData[INDEX_OF_DATE];
                LocalDate localDate = LocalDate.parse(dateFromData, DATE_TIME_FORMATTER);
                if (!localDate.isBefore(fromDate) && !localDate.isAfter(toDate)
                        && nameFromData.equals(name)) {
                    totalSalary += Integer.parseInt(workingHours) * Integer.parseInt(hourSalary);
                }
            }
            builder.append(totalSalary);
        }
        return builder.toString();
    }
}
