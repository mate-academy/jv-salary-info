package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static String getSalaryInfo(
            String[] names,
            String[] data,
            String dateFrom,
            String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        int[] result = new int[names.length];//зберігає результат

        for (String record : data) {

            String[] parts = record.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String nameOfRecord = parts[1];
            int workHour = Integer.parseInt(parts[2]);
            int salaryPerHour = Integer.parseInt(parts[3]);

            if (workDate.isAfter(from) && workDate.isBefore(to)
                    || workDate.isEqual(from) || workDate.isEqual(to)) {
                for (int i = 0; i < result.length; i++) {
                    if (names[i].equals(nameOfRecord)) {
                        result[i] += workHour * salaryPerHour;
                    }
                }
            }
        }

        stringBuilder
                .append("Report for period " + dateFrom + " - " + dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            stringBuilder
                    .append(names[i]).append(" - ")
                    .append(result[i])
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
