package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte DATE = 0;
    private static final byte NAME = 1;
    private static final byte HOUR = 2;
    private static final byte RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        int salary = 0;
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String[] datum = data[i].split(" ");
                if (names[j].equals(datum[NAME])
                        && (LocalDate.parse(datum[DATE], FORMATTER).isAfter(startDate)
                        && LocalDate.parse(datum[DATE], FORMATTER).isBefore(endDate))) {
                    salary = salary + Integer.parseInt(datum[HOUR]) * Integer.parseInt(datum[RATE]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(names[j])
                    .append(" - ")
                    .append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
