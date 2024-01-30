package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final String REPORT_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (dateFrom == null || dateTo == null || names.length == 0 || data.length == 0) {
            return "Invalid data. Dates should not be null,"
                    + "also names and data should not be empty";
        }
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ")
                    .append(dateFrom)
                    .append(REPORT_SEPARATOR)
                    .append(dateTo)
                    .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int earningsForName = 0;
            for (String dataUnit : data) {
                String[] parts = dataUnit.split(" ");
                if (parts.length == 4) {
                    LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                    if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                        String name = parts[NAME_INDEX];
                        int hours = Integer.parseInt(parts[HOURS_INDEX]);
                        int perHour = Integer.parseInt(parts[PER_HOUR_INDEX]);
                        if (name.equals(names[i])) {
                            earningsForName += hours * perHour;
                        }
                    }
                }
            }
            result.append(names[i])
                        .append(REPORT_SEPARATOR)
                        .append(earningsForName);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
