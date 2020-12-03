package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate periodStart = LocalDate.parse(dateFrom, FORMAT);
        LocalDate periodEnd = LocalDate.parse(dateTo, FORMAT);

        StringBuilder report = new StringBuilder("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int income = 0;
            for (String info : data) {
                String[] splitInfo = info.split(" ");
                LocalDate workingDate = LocalDate.parse(splitInfo[0], FORMAT);
                String reportName = splitInfo[1];
                int hoursWorked = Integer.parseInt(splitInfo[2]);
                int hourlyRate = Integer.parseInt(splitInfo[3]);

                if (reportName.equals(name) && (workingDate.isAfter(periodStart) || workingDate.isEqual(periodStart)) && (workingDate.isBefore(periodEnd) || workingDate.isEqual(periodEnd))) {
                    income += hoursWorked * hourlyRate;
                }
            }
            report.append("\n").append(name).append(" - ").append(income);
        }
        return report.toString();

    }
}
