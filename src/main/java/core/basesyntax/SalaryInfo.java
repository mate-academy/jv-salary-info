package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private int getNameIndex(String[] names, String name) {
        return IntStream.range(0, names.length)
                .filter(i -> name.equals(names[i]))
                .findFirst()
                .orElse(-1);
    }

    private int getPayout(String[] record) {
        return Integer.valueOf(record[2]) * Integer.valueOf(record[3]);
    }

    private boolean isDateInPeriod(LocalDate date, LocalDate[] period) {
        return date.isAfter(period[0])
                && date.isBefore(period[1]) || date.isEqual(period[1]);
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate[] period = new LocalDate[]{
                LocalDate.parse(dateFrom, FORMATTER),
                LocalDate.parse(dateTo, FORMATTER)
        };
        int[] salary = new int[names.length];
        for (String record : data) {
            String[] recordArray = record.split(" ");
            LocalDate dateInRecord = LocalDate.parse(recordArray[0], FORMATTER);
            if (isDateInPeriod(dateInRecord, period)) {
                salary[getNameIndex(names, recordArray[1])] += getPayout(recordArray);
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }
        return stringBuilder.toString();
    }
}
