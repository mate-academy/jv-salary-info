package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOUR = 2;
    private static final int INDEX_OF_MONEY_PER_HOUR = 3;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name: names) {
            int totalSalary = 0;
            for (String dates: data) {
                String [] part = dates.split(" ");
                LocalDate recordDate = LocalDate.parse(part[INDEX_OF_DATE],FORMATTER);

                if (name.equals(part[INDEX_OF_NAME])
                        && isDatesIncludes(recordDate, startDate, endDate)) {
                    totalSalary +=
                            Integer.parseInt(part[INDEX_OF_HOUR])
                                    * Integer.parseInt(part[INDEX_OF_MONEY_PER_HOUR]);
                }
            }
            stringBuilder
                    .append(LINE_SEPARATOR)
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return stringBuilder.toString();
    }

    private boolean isDatesIncludes(LocalDate localDate, LocalDate dateFrom, LocalDate dateTo) {
        return (localDate.isAfter(dateFrom) || localDate.equals(dateFrom))
                && (localDate.isBefore(dateTo) || localDate.equals(dateTo));
    }
}
