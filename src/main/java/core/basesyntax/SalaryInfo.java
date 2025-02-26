package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        int[] salaries = new int[names.length];

        for (String instanceOfData : data) {
            String[] parts = instanceOfData.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0],
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            if (!currentDate.isBefore(from) && !currentDate.isAfter(to)) {
                String name = parts[1];
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        int salary = hours * rate;
                        salaries[i] += salary;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
            if (i < names.length - 1) {
                report.append("\n");
            }
        }

        return report.toString();
    }
}
