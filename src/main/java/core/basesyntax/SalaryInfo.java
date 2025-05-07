package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PAYMENT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salary = new int[names.length];
        for (String line : data) {
            String [] dataFromLine = line.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (isIn(LocalDate.parse(dataFromLine[DATE_INDEX], FORMATTER),fromDate,toDate)
                        && dataFromLine[NAME_INDEX].equals(names[i])) {
                    salary[i] += Integer.parseInt(dataFromLine[HOURS_INDEX])
                            * Integer.parseInt(dataFromLine[PAYMENT_INDEX]);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        for (int i = 0; i < salary.length; i++) {
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary[i]);
        }
        return stringBuilder.toString();
    }

    private boolean isIn(LocalDate date, LocalDate from, LocalDate to) {
        return ((date.isAfter(from) || date.isEqual(from))
                && (date.isBefore(to) || date.isEqual(to)));
    }
}
