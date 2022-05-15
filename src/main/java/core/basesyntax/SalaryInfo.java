package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(LINE_SEPARATOR);
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] lineInfo = line.split(" ");
                LocalDate current = LocalDate.parse(lineInfo[DATE_INDEX], DATE_TIME_FORMATTER);
                int hours = Integer.parseInt(lineInfo[HOURS_INDEX]);
                int pay = Integer.parseInt(lineInfo[RATE_INDEX]);
                if (lineInfo[NAME_INDEX].equals(name) && (current.isAfter(from)
                        || current.isEqual(from)) && (current.isBefore(to)
                        || current.isEqual(to))) {
                    salary += hours * pay;
                }
            }
            result.append(name).append(" - ").append(salary).append(LINE_SEPARATOR);
        }
        return result.toString().trim();
    }
}
