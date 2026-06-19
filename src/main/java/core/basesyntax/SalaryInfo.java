package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();

        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            int salary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");

                LocalDate currentDate = LocalDate.parse(parts[0], formatter);
                String employeeName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                if (employeeName.equals(names[i]) && !currentDate.isBefore(from)
                        && !currentDate.isAfter(to)) {

                    salary += hours * rate;
                }
            }

            report.append(names[i])
                    .append(" - ")
                    .append(salary);

            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();

    }

}

