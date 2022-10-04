package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_DATE_POSITION = 0;
    private static final int DATA_HOUR_POSITION = 2;
    private static final int DATA_SAlARY_POSITION = 3;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SPACER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getReportPrefix(dateFrom, dateTo));
        LocalDate lowBorder = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate topBorder = LocalDate.parse(dateTo, FORMATTER);
        topBorder = topBorder.plusDays(1);
        for (String name : names) {
            int salarySum = 0;
            for (String dataLine : data) {
                if (dataLine.indexOf(name) > 0) {
                    String[] dataElements = dataLine.split(SPACER);
                    LocalDate dataDate = LocalDate.parse(
                            dataElements[DATA_DATE_POSITION],
                            FORMATTER
                    );
                    int workingHours = Integer.parseInt(dataElements[DATA_HOUR_POSITION]);
                    int salary = Integer.parseInt(dataElements[DATA_SAlARY_POSITION]);
                    if ((dataDate.equals(lowBorder) || dataDate.isAfter(lowBorder))
                            && dataDate.isBefore(topBorder)) {
                        salarySum += salary * workingHours;
                    }
                }
            }
            stringBuilder.append(LINE_SEPARATOR)
                    .append(name)
                    .append(" - ")
                    .append(salarySum);
        }
        return stringBuilder.toString();
    }

    private String getReportPrefix(String dateFrom, String dateTo) {
        return String.format(
                "Report for period %s - %s",
                dateFrom,
                dateTo
        );
    }
}
