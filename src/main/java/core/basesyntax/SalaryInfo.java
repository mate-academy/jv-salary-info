package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] parts = record.split(" ");
                String recordName = parts[NAME_INDEX];
                if (name.equals(recordName)) {
                    LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
                    if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                        int hours = Integer.parseInt(parts[HOURS_INDEX]);
                        int rate = Integer.parseInt(parts[RATE_INDEX]);
                        totalSalary += hours * rate;
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }
}
