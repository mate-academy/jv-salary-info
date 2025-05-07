package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT_FOR_PERIOD = "Report for period";
    private static final String WHITESPACE = " ";
    private static final String DASH = "-";
    private static final String PATTERN = "d.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_WORKING_HOUR = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;
    private static final int DAY_INCLUSIVE = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append(REPORT_FOR_PERIOD).append(WHITESPACE)
                .append(dateFrom).append(WHITESPACE).append(DASH)
                .append(WHITESPACE).append(dateTo);
        LocalDate from = reformatToLocalDate(dateFrom).minusDays(DAY_INCLUSIVE);
        LocalDate to = reformatToLocalDate(dateTo).plusDays(DAY_INCLUSIVE);
        for (String name : names) {
            int salarySum = 0;
            for (String rowData : data) {
                String[] rowDataToArr = rowData.split(WHITESPACE);
                LocalDate dateFromData = reformatToLocalDate(rowDataToArr[INDEX_OF_DATE]);
                int workingHour = Integer.parseInt(rowDataToArr[INDEX_OF_WORKING_HOUR]);
                int salaryPerHour = Integer.parseInt(rowDataToArr[INDEX_OF_SALARY_PER_HOUR]);
                if (rowData.contains(name) && from.isBefore(dateFromData)
                        && to.isAfter(dateFromData)) {
                    salarySum += workingHour * salaryPerHour;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(WHITESPACE).append(DASH)
                    .append(WHITESPACE).append(salarySum);

        }
        return builder.toString();
    }

    private LocalDate reformatToLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
