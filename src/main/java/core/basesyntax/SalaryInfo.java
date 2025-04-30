package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        int[] salary = new int[names.length];

        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);

        for (String datum : data) {
            String[] lines = datum.split(" ");
            LocalDate currentDate = LocalDate.parse(lines[0], FORMATTER);

            if ((currentDate.isAfter(fromDate) || currentDate.isEqual(fromDate))
                    && (currentDate.isBefore(toDate) || currentDate.isEqual(toDate))) {
                for (int j = 0; j < names.length; j++) {
                    if (lines[1].equals(names[j])) {
                        salary[j] += Integer.parseInt(lines[2]) * Integer.parseInt(lines[3]);
                    }
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(salary[i]);
        }
        return stringBuilder.toString();
    }
}
