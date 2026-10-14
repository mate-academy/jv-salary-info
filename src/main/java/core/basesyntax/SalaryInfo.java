package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate workDate = LocalDate.parse(parts[0], DATE_FORMATTER);
                String employeeName = parts[1];
                int hours = Integer.parseInt(parts[2]);
                int incomePerHour = Integer.parseInt(parts[3]);

                if (employeeName.equals(name)
                        && !workDate.isBefore(fromDate)
                        && !workDate.isAfter(toDate)) {
                    totalSalary += hours * incomePerHour;
                }
            }

            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }

        return report.toString();
    }
}
