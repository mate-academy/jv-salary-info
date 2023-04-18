package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] parts = datum.split(" ");
                String dateString = parts[0];
                String employeeName = parts[1];
                if (employeeName.equals(name)) {
                    LocalDate date = LocalDate.parse(dateString, FORMATTER);
                    if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        salary += hours * rate;
                    }
                }
            }
            sb.append(System.lineSeparator()).append(String.format("%s - %d", name, salary));
        }
        return sb.toString();
    }
}
