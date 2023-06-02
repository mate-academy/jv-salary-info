package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int HOURLY_INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder targetArray = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int sumSolary = 0;
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            for (String dataRow : data) {
                String[] splitting = dataRow.split(" ");
                LocalDate dataDate = LocalDate.parse(splitting[DATE_INDEX], formatter);
                if (splitting[NAME_INDEX].equals(name) && (dataDate.isAfter(startDate)
                        || dataDate.isEqual(startDate)) && (dataDate.isBefore(endDate)
                        || dataDate.isEqual(endDate))) {
                    sumSolary += Integer.parseInt(splitting[HOUR_INDEX])
                            * Integer.parseInt(splitting[HOURLY_INCOME_INDEX]);
                }
            }
            targetArray
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sumSolary);
            sumSolary = 0;
        }
        return targetArray.toString();
    }
}
