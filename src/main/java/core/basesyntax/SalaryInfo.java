package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_DATE_POSITION = 0;
    private static final int DATA_HOUR_POSITION = 2;
    private static final int DATA_SAlARY_POSITION = 3;
    private final StringBuilder stringBuilder = new StringBuilder();
    private LocalDate lowBorder;
    private LocalDate topBorder;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        stringBuilder.setLength(0);
        stringBuilder.append(getReportPrefix(dateFrom, dateTo));
        lowBorder = LocalDate.parse(dateFrom, formatter);
        topBorder = LocalDate.parse(dateTo, formatter);
        topBorder = topBorder.plusDays(1);
        for (String name : names) {
            int salarySum = 0;
            for (String dataLine : data) {
                if (dataLine.indexOf(name) > 0) {
                    String[] dataElements = dataLine.split(" ");
                    LocalDate dataDate = LocalDate.parse(
                            dataElements[DATA_DATE_POSITION],
                            formatter
                    );
                    int workingHours = Integer.parseInt(dataElements[DATA_HOUR_POSITION]);
                    int salary = Integer.parseInt(dataElements[DATA_SAlARY_POSITION]);
                    if ((dataDate.equals(lowBorder) || dataDate.isAfter(lowBorder))
                            && dataDate.isBefore(topBorder)) {
                        salarySum += salary * workingHours;
                    }
                }
            }
            stringBuilder.append(name)
                    .append(" - ")
                    .append(salarySum)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    private String getReportPrefix(String dateFrom, String dateTo) {
        return String.format(
                "Report for period %s - %s" + System.lineSeparator(),
                dateFrom,
                dateTo
        );
    }
}
