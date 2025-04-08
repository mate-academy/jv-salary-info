package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromCheck = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToCheck = LocalDate.parse(dateTo, formatter);

        int[] salary = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String line : data) {
                String[] parts = line.trim().split("\\s+"); // podziel po białych znakach
                String date = parts[0];
                LocalDate preciseDate = LocalDate.parse(date, formatter);// data: "26.04.2019"
                String name = parts[1];
                int days = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                if (!preciseDate.isBefore(dateFromCheck) && !preciseDate.isAfter(dateToCheck)) {
                    if (names[i].equals(name)) {
                        salary[i] = salary[i] + (days * rate);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (int j = 0; j < names.length; j++) {
            sb.append(names[j]).append(" - ").append(salary[j]);
            if (j < names.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
