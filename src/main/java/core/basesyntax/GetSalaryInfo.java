package core.basesyntax;

import java.time.LocalDate;


import java.time.format.DateTimeFormatter;

public class GetSalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate To = LocalDate.parse(dateto, formatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateto)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;


            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate workDate = LocalDate.parse(parts[0], formatter);
                String employee = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);

                if (!workDate.isBefore(from) && !workDate.isAfter(To) && employee.equals(name)) {
                    totalSalary += hours * rate;
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
