package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(getSalaryForEmploye(name, data, dateFrom, dateTo));
        }
        return stringBuilder.toString();
    }

    public int getSalaryForEmploye(String name, String[] data, String dateFrom, String dateTo) {
        int salaryCounter = 0;
        for (String datum : data) {
            String[] date = datum.split(" ");
            if (isDayInLimits(date[0], dateFrom, dateTo) && date[1].equals(name)) {
                salaryCounter += Integer.parseInt(date[2]) * Integer.parseInt(date[3]);
            }
        }
        return salaryCounter;
    }

    public boolean isDayInLimits(String date, String dateFrom, String dateTo) {
        LocalDate testDate = LocalDate.parse(date, formatter);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        return (testDate.isEqual(endDate) || testDate.isEqual(startDate)
                || testDate.isAfter(startDate) && testDate.isBefore(endDate));
    }
}
