package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salaryPerPeriod = 0;
            for (String d : data) {
                String[] sd = d.split(" ");
                if (sd[1].equals(name) && isDateInRange(sd[0], dateFrom, dateTo)) {
                    int hours = Integer.parseInt(sd[2]);
                    int salaryPerHour = Integer.parseInt(sd[3]);
                    salaryPerPeriod += hours * salaryPerHour;
                }
            }
            result.append(System.lineSeparator()).append(name)
                    .append(" - ").append(salaryPerPeriod);
        }
        return result.toString();
    }

    private boolean isDateInRange(String date, String dateFrom, String dateTo) {
        LocalDate dateToCheck = LocalDate.parse(date, DATE_TIME_FORMATTER);
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        return dateToCheck.isAfter(startDate) && !dateToCheck.isAfter(endDate);
    }
}
