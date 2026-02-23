package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String RECORD_DELIMITER = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String REPORT_PREFIX = "Report for period ";
    private static final String DATE_RANGE_SEPARATOR = "  - ";
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        for (int recordIndex = 0; recordIndex < data.length; recordIndex++) {
            String[] recordParts = data[recordIndex].split(RECORD_DELIMITER);
            LocalDate entryDate =
                    LocalDate.parse(recordParts[DATE_INDEX], FORMATTER);
            boolean isDateInRange =
                    !entryDate.isBefore(from) && !entryDate.isAfter(to);
            if (isDateInRange) {
                for (int nameIndex = 0; nameIndex < names.length; nameIndex++) {
                    if (names[nameIndex].equals(recordParts[NAME_INDEX])) {
                        int hours =
                                Integer.parseInt(recordParts[HOURS_INDEX]);
                        int betPerHours =
                                Integer.parseInt(recordParts[RATE_INDEX]);

                        salary[nameIndex] += hours * betPerHours;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append(REPORT_PREFIX)
                .append(dateFrom)
                .append(DATE_RANGE_SEPARATOR)
                .append(dateTo);
        for (int nameIndex = 0; nameIndex < names.length; nameIndex++) {
            report.append(LINE_SEPARATOR)
                    .append(names[nameIndex])
                    .append(" - ")
                    .append(salary[nameIndex]);
        }
        return report.toString();
    }
}
