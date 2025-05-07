package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int NUMBER_OF_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sum = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate dateFromLine = LocalDate.parse(splittedLine[DATE_INDEX], FORMATTER);
                int incomePerDay = Integer.parseInt(splittedLine[NUMBER_OF_HOURS_INDEX])
                        * Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                if (name.equals(splittedLine[NAME_INDEX])
                        && (dateFromLine.isAfter(parsedDateFrom)
                        || dateFromLine.equals(parsedDateFrom))
                        && (dateFromLine.isBefore(parsedDateTo)
                        || dateFromLine.equals(parsedDateTo))) {
                    sum += incomePerDay;
                }
            }
            builder.append("\n").append(name).append(" - ").append(sum);
        }
        return builder.toString();
    }
}
