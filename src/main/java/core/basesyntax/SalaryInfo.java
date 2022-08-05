package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private static final String START_TOPIC = "Report for period ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.M.yyyy");

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
                if (name.equals(info[1])) {
                    LocalDate date = LocalDate.parse(info[0], FORMATTER);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
                        salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                    }
                }
            }
            builder.append(name + " - " + salary + System.lineSeparator());
        }

        int indexOfSeparatpr = builder.lastIndexOf(System.lineSeparator());
        return builder.subSequence(0, indexOfSeparatpr).toString();
    }
}
