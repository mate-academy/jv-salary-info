package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_COLUMN_INDEX = 0;
    public static final int NAME_COLUMN_INDEX = 1;
    public static final int HOUR_COLUMN_INDEX = 2;
    public static final int PRICE_COLUMN_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder reportStringBuilder = new StringBuilder();
        reportStringBuilder
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int personalSalary = 0;

            for (String salaryInfoColumn : data) {
                String[] splitInfo = salaryInfoColumn.split(" ");
                LocalDate actualDate = LocalDate.parse(splitInfo[DATE_COLUMN_INDEX],
                        DATE_FORMATTER);

                if (!name.equals(splitInfo[NAME_COLUMN_INDEX])) {
                    continue;
                }

                if (actualDate.isBefore(fromDate) || actualDate.isAfter(toDate)) {
                    continue;
                }

                int salaryHour = Integer.parseInt(splitInfo[HOUR_COLUMN_INDEX]);
                int salaryPrice = Integer.parseInt(splitInfo[PRICE_COLUMN_INDEX]);
                personalSalary += salaryHour * salaryPrice;
            }

            reportStringBuilder
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(personalSalary);
        }
        return reportStringBuilder.toString();
    }
}
