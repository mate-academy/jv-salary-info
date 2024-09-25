package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private static final int NAME_INDEX = 1;
    private static final String DATA_DELIMITER = " ";
    private static final String REPORT_DELIMITER = " - ";
    private static final String REPORT_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder(REPORT_HEADER).append(dateFrom)
                .append(REPORT_DELIMITER)
                .append(dateTo);
        int[] salaries = new int[names.length];
        for (String info : data) {
            String[] parts = info.split(DATA_DELIMITER);
            LocalDate recordDay = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
            if ((recordDay.isAfter(fromDate) || recordDay.isEqual(fromDate)) && (
                    recordDay.isBefore(toDate) || recordDay.isEqual(toDate))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(parts[NAME_INDEX])) {
                        salaries[i] += Integer.parseInt(parts[HOURS_INDEX]) * Integer.parseInt(
                                parts[INCOME_INDEX]);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append((names[i]))
                    .append(REPORT_DELIMITER)
                    .append(salaries[i]);
        }
        return report.toString();
    }
}
