package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
        for (String employ : names) {
            int salary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                String date = parts[0];
                String name = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);
                LocalDate entryDate = LocalDate.parse(date, dateFormatter);
                if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                    if (employ.equals(name)) {
                        salary += hours * incomePerHour;
                    }
                }
            }
            report.append(System.lineSeparator()).append(employ).append(" - ").append(salary);
        }
        return report.toString();
    }
}
