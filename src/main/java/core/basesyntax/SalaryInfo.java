package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate to = LocalDate.parse(dateTo.trim(), FORMATTER);

        String[] resultList = new String[names.length];

        for (int n = 0; n < names.length; n++) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] parts = data[i].split(" ");
                LocalDate date = LocalDate.parse(parts[0], FORMATTER);
                if (date.isBefore(from) || date.isAfter(to)) {
                    continue;
                }
                if (parts[1].equals(names[n])) {
                    salary += Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                }

            }
            resultList[n] = names[n] + " - " + salary;
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom.trim() + " - " + dateTo.trim() + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            if (i > 0) {
                stringBuilder.append(System.lineSeparator());
            }
            stringBuilder.append(resultList[i]);
        }
        return stringBuilder.toString();
    }
}
