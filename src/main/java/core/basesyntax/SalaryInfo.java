package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int FIRST_ELEMENT = 0;
    private static final int NAME_POSITION = 1;
    private static final int QUANTITY_OF_HOURS = 2;
    private static final int HOURS_RATE = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] splitData;
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                splitData = datum.split(" ");
                LocalDate date = LocalDate.parse(splitData[FIRST_ELEMENT], DATE_TIME_FORMATTER);
                if (name.equals(splitData[NAME_POSITION])
                        && ((date.isAfter(localDateFrom) && date.isBefore(localDateTo))
                        || date.equals(localDateFrom) || date.equals(localDateTo))) {
                    salary += Integer.parseInt(splitData[QUANTITY_OF_HOURS])
                            * Integer.parseInt(splitData[HOURS_RATE]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
