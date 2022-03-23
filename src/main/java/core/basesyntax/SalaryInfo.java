package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] array;
        int salary = 0;
        LocalDate dateFromDateLocalDate = getDateFromString(dateFrom, FORMAT);
        LocalDate dateToDateLocalDate = getDateFromString(dateTo, FORMAT);
        for (String name : names) {
            for (String datum : data) {
                array = datum.split(" ");
                if (dateFromDateLocalDate.isAfter(getDateFromString(array[0], FORMAT))
                        || dateToDateLocalDate
                        .isBefore(getDateFromString(array[0], FORMAT))) {
                    continue;
                }
                if (array[1].equals(name)) {
                    salary = salary + Integer.parseInt(array[2]) * Integer.parseInt(array[3]);
                }
            }

            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return builder.toString();
    }

    public static LocalDate getDateFromString(String string, DateTimeFormatter format) {
        return LocalDate.parse(string, format);
    }
}
