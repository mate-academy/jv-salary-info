package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate to = LocalDate.parse(dateTo, dateFormatter);
        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[0], dateFormatter);
            String nameInRecord = parts[1];
            if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                // знайди ім'я в масиві names, порахуй зарплату, додай
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(nameInRecord)) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        salaries[i] += hours * rate;
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ");
        result.append(dateFrom);
        result.append(" - ");
        result.append(dateTo);
        result.append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]);
            result.append(" - ");
            result.append(salaries[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
