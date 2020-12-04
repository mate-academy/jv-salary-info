package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate periodStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate periodEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String namePerson : names) {
            int income = 0;
            for (String rowData : data) {
                String[] splitData = rowData.split(" ");
                LocalDate workingDate = LocalDate.parse(splitData[0], DATE_TIME_FORMATTER);
                String reportName = splitData[1];
                int hoursWorked = Integer.parseInt(splitData[2]);
                int hourlyRate = Integer.parseInt(splitData[3]);

                if (reportName.equals(namePerson)
                        && (workingDate.isAfter(periodStart)
                        || workingDate.isEqual(periodStart))
                        && (workingDate.isBefore(periodEnd)
                        || workingDate.isEqual(periodEnd))) {
                    income += hoursWorked * hourlyRate;
                }
            }
            report.append("\n").append(namePerson).append(" - ").append(income);
        }
        return report.toString();
    }
}
