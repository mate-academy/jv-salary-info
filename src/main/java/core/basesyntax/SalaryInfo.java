package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private static final String START_TOPIC = "Report for period ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.M.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder(
                START_TOPIC + dateFrom + " - " + dateTo + System.lineSeparator()
        );

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] info = record.split(SEPARATOR);
                if (name.equals(info[NAME])) {
                    LocalDate date = LocalDate.parse(info[DATE], FORMATTER);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
                        salary += Integer.parseInt(info[HOURS])
                                * Integer.parseInt(info[INCOME_PER_HOUR]);
                    }
                }
            }
            builder.append(name + " - " + salary + System.lineSeparator());
        }

        int indexOfSeparatpr = builder.lastIndexOf(System.lineSeparator());
        return builder.subSequence(0, indexOfSeparatpr).toString();
    }
}
