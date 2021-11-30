package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte INDEX_OF_DATE = 0;
    private static final byte INDEX_OF_NAME = 1;
    private static final byte INDEX_OF_HOURS = 2;
    private static final byte INDEX_OF_PAYMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
            int salary = 0;
            for (String line : data) {
                String[] splitLine = line.split(" ");
                LocalDate dateFromLine = LocalDate
                        .parse(splitLine[INDEX_OF_DATE], DATE_FORMATTER);
                if (!(dateFromLine.isBefore(startDate) || dateFromLine.isAfter(endDate))
                        && splitLine[INDEX_OF_NAME].equals(names[i])) {
                    salary += Integer.parseInt(splitLine[INDEX_OF_HOURS])
                            * Integer.parseInt(splitLine[INDEX_OF_PAYMENT]);
                }
            }
            reportBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary);
        }
        return reportBuilder.toString();
    }
}
