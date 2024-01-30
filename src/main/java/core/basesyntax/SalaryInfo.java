package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String HEAD_MESSAGE = "Report for period %s - %s" + System.lineSeparator();
    private static final String BODY_MESSAGE = "%s - %s";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        int[] totalSalary = new int[names.length];
        int nameIndex = 0;

        report.append(String.format(HEAD_MESSAGE, dateFrom, dateTo));

        for (int i = 0; i < data.length; i++) {

            if (isRowInDateRange(data[i], dateFrom, dateTo)) {
                /* If current row name matches e.g. names[2],
                it will always add values to totalSalary[2]. */
                nameIndex = extractPartFromRow(data[i], 1).equals(names[0]) ? 0 :
                        extractPartFromRow(data[i], 1).equals(names[1]) ? 1 :
                                extractPartFromRow(data[i], 1).equals(names[2]) ? 2 : 0;

                totalSalary[nameIndex] += Integer.parseInt(extractPartFromRow(data[i], 2))
                        * Integer.parseInt(extractPartFromRow(data[i], 3));
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(String.format(BODY_MESSAGE, names[i], totalSalary[i]));

            if (i < (names.length - 1)) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }

    private boolean isRowInDateRange(String currentRow, String dateFrom, String dateTo) {
        String[] parts = currentRow.split(" ");
        LocalDate inputDateRow = LocalDate.parse(parts[0], formatter);
        LocalDate inputDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate inputDateTo = LocalDate.parse(dateTo, formatter);

        return !inputDateRow.isBefore(inputDateFrom) && !inputDateRow.isAfter(inputDateTo);
    }

    private String extractPartFromRow(String currentRow, int itemIndex) {
        String[] parts = currentRow.split(" ");

        return parts[itemIndex];
    }
}
