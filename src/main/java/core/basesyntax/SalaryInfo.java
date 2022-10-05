package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int INDEX_DATE = 0;
    public static final int INDEX_NAME = 1;
    public static final int INDEX_NUMBER_OF_AMOUNT = 2;
    public static final int INDEX_AMOUNT = 3;
    public static final int INDEX_DATE_FROM = 0;
    public static final int INDEX_DATE_TO = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate[] period = new LocalDate[]{
                LocalDate.parse(dateFrom, FORMATTER),
                LocalDate.parse(dateTo, FORMATTER)
        };
        int[] salary = new int[names.length];
        for (String record : data) {
            String[] recordArray = record.split(" ");
            LocalDate dateInRecord = LocalDate.parse(recordArray[INDEX_DATE], FORMATTER);
            if (isDateInPeriod(dateInRecord, period)) {
                salary[getNameIndex(names, recordArray[INDEX_NAME])] += getPayout(recordArray);
            }
        }
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return result.toString();
    }

    private int getNameIndex(String[] names, String name) {
        return IntStream.range(0, names.length)
                .filter(i -> name.equals(names[i]))
                .findFirst()
                .orElse(-1);
    }

    private int getPayout(String[] record) {
        return Integer.valueOf(record[INDEX_NUMBER_OF_AMOUNT])
                * Integer.valueOf(record[INDEX_AMOUNT]);
    }

    private boolean isDateInPeriod(LocalDate date, LocalDate[] period) {
        return date.isAfter(period[INDEX_DATE_FROM]) && date.isBefore(period[INDEX_DATE_TO])
                || date.isEqual(period[INDEX_DATE_TO]);
    }
}
