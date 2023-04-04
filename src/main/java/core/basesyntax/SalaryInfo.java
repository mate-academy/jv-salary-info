package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final String SPLIT_SEPARATOR = " ";
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final String LINE_SEPARATOR = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> earnings = new HashMap<>();
        for (String name : names) {
            earnings.put(name, 0);
        }

        for (String line : data) {
            String[] parts = line.split(SPLIT_SEPARATOR);
            LocalDate date = LocalDate.parse(parts[DATE_INDEX], formatter);
            if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {
                String name = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[SALARY_PER_INDEX]);
                int amount = hours * rate;
                earnings.put(name, earnings.get(name) + amount);
            }
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom)
                .append(LINE_SEPARATOR).append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            reportBuilder.append(name).append(LINE_SEPARATOR)
                    .append(earnings.get(name))
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString().trim();
    }
}
