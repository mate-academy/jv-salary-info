package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int salary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate currentDate = LocalDate.parse(parts[0], formatter);
                String employeeName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                if (employeeName.equals(name)
                        && !currentDate.isAfter(to)
                        && !currentDate.isBefore(from)) {
                    salary += hours * rate;
                }
            }

            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
