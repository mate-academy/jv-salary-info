package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final String INVALID_DATA_FORMAT = "Invalid data format";
    private static final String REPORT_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder(REPORT_HEADER)
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String entry: data) {
                String[] parts = entry.split(" ");
                if (parts.length == 4) {
                    LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                    String name = parts[NAME_INDEX];
                    int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
                    int incomePerHour = Integer.parseInt(parts[INCOME_INDEX]);
                    if (!entryDate.isAfter(toDate) && !entryDate.isBefore(fromDate)) {
                        if (names[i].equals(name)) {
                            salary += hoursWorked * incomePerHour;
                        }
                    }
                } else {
                    return INVALID_DATA_FORMAT;
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return result.toString();
    }
}
