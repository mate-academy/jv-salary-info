package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " ";
    private static final String START_TOPIC = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = stringToDate(dateFrom);
        LocalDate to = stringToDate(dateTo);
        StringBuilder builder = new StringBuilder(
                START_TOPIC + dateFrom + " - " + dateTo + System.lineSeparator()
        );

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] info = record.split(SEPARATOR);
                if (name.equals(info[1])) {
                    LocalDate date = stringToDate(info[0]);
                    if (date.compareTo(from) >= 0 && date.compareTo(to) <= 0) {
                        salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                    }
                }
            }
            builder.append(name + " - " + salary + System.lineSeparator());
            salary = 0;
        }

        int indexOfSeparatpr = builder.lastIndexOf(System.lineSeparator());
        return builder.subSequence(0, indexOfSeparatpr).toString();
    }

    private LocalDate stringToDate(String date) {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd.M.yyyy");
        return LocalDate.parse(date, formater);
    }
}
