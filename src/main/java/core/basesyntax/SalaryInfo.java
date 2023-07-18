package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateForm);
        LocalDate toDate = LocalDate.parse(dateTo, dateForm);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String emloyeeData : data) {
                String[] parts = emloyeeData.split(" ");
                LocalDate date = LocalDate.parse(parts[0], dateForm);

                if (date.isAfter(toDate) || date.isBefore(fromDate)) {
                    continue;
                }
                if (parts[1].equals(name)) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
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
