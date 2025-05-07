package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int INDEX_NAME = 1;
    public static final int INDEX_HOUR = 2;
    public static final int INDEX_BET = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int salary = 0;
            for (String line: data) {
                String[] dataParts = line.split(" ");
                LocalDate localDate = LocalDate.parse(dataParts[0], FORMATTER);
                boolean isAfterOrEquals = localDate.isAfter(localDateFrom)
                        || localDate.equals(localDateFrom);
                boolean isBeforeOrEquals = localDate.isBefore(localDateTo)
                        || localDate.equals(localDateTo);
                boolean isNameEquals = dataParts[INDEX_NAME].equals(name);
                if (isAfterOrEquals && isBeforeOrEquals && isNameEquals) {
                    salary += Integer.parseInt(dataParts[INDEX_HOUR])
                            * Integer.parseInt(dataParts[INDEX_BET]);

                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ")
                    .append(salary);
        }
        return report.toString();
    }
}
