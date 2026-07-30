package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String LINE_PREFIX = "   ";

    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder(LINE_PREFIX)
                .append("Report for period ")
                .append(dateFrom)
                .append("  - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] recordInfo = record.split(" ");
                LocalDate workDate = LocalDate.parse(recordInfo[DATE_INDEX], DATE_FORMATTER);
                if (recordInfo[NAME_INDEX].equals(name)
                        && !workDate.isBefore(startDate)
                        && !workDate.isAfter(endDate)) {
                    int hours = Integer.parseInt(recordInfo[HOURS_INDEX]);
                    int rate = Integer.parseInt(recordInfo[RATE_INDEX]);
                    salary += hours * rate;
                }
            }
            report.append(System.lineSeparator())
                    .append(LINE_PREFIX)
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
