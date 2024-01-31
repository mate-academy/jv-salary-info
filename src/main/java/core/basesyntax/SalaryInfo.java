package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String HEAD_MESSAGE = "Report for period %s - %s";
    private static final String BODY_MESSAGE = "%s - %s";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private StringBuilder report;
    private int[] totalSalary;
    private int currentNameIndex;
    private LocalDate localDateFrom;
    private LocalDate localDateTo;
    private LocalDate localDateCurrent;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        report = new StringBuilder(String.format(HEAD_MESSAGE, dateFrom, dateTo));
        totalSalary = new int[names.length];
        localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        localDateTo = LocalDate.parse(dateTo, FORMATTER);

        for (int i = 0; i < data.length; i++) {
            localDateCurrent = LocalDate.parse((extractPartFromRow(data[i], 0)), FORMATTER);

            if (isRowInDateRange(localDateCurrent, localDateFrom, localDateTo)) {
                /* If current row name matches e.g. names[2],
                it will always add values to totalSalary[2]. */
                currentNameIndex = extractPartFromRow(data[i], 1).equals(names[0]) ? 0 :
                        extractPartFromRow(data[i], 1).equals(names[1]) ? 1 :
                                extractPartFromRow(data[i], 1).equals(names[2]) ? 2 : 0;

                totalSalary[currentNameIndex] += Integer.parseInt(extractPartFromRow(data[i], 2))
                        * Integer.parseInt(extractPartFromRow(data[i], 3));
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(String.format(BODY_MESSAGE, names[i], totalSalary[i]));
        }
        return report.toString();
    }

    private boolean isRowInDateRange(LocalDate dateToCheck, LocalDate dateFrom, LocalDate dateTo) {
        return !dateToCheck.isBefore(dateFrom) && !dateToCheck.isAfter(dateTo);
    }

    private String extractPartFromRow(String currentRow, int itemIndex) {
        String[] parts = currentRow.split(" ");
        return parts[itemIndex];
    }
}
