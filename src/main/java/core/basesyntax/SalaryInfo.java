package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate periodStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate periodEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        StringBuilder str = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String namePerson : names) {
            int income = 0;
            for (String s : data) {
                String[] split = s.split(" ");
                LocalDate workingDate = LocalDate.parse(split[0], DATE_TIME_FORMATTER);
                String reportName = split[1];
                int hoursWorked = Integer.parseInt(split[2]);
                int hourlyRate = Integer.parseInt(split[3]);

                if (reportName.equals(namePerson)
                        && (workingDate.isAfter(periodStart)
                        || workingDate.isEqual(periodStart))
                        && (workingDate.isBefore(periodEnd)
                        || workingDate.isEqual(periodEnd))) {
                    income += hoursWorked * hourlyRate;
                }
            }
            str.append("\n").append(namePerson).append(" - ").append(income);
        }
        return str.toString();
    }
}
