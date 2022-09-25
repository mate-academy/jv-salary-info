package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte DATE_INDEX = 0;
    private static final byte NAME_INDEX = 1;
    private static final byte HOUR_INDEX = 2;
    private static final byte RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] datum = record.split(" ");
                if (name.equals(datum[NAME_INDEX])
                        && (LocalDate.parse(datum[DATE_INDEX], FORMATTER).isAfter(startDate)
                        && LocalDate.parse(datum[DATE_INDEX], FORMATTER).isBefore(endDate))) {
                    salary = salary + Integer.parseInt(datum[HOUR_INDEX]) * Integer.parseInt(datum[RATE_INDEX]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
