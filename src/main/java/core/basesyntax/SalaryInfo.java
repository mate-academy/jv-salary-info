package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
        for (String date : data) {
            String[] employe = date.split(" ");
            if (isDateInLimits(employe[DATA_INDEX], dateFrom, dateTo)
                    && employe[NAME_INDEX].equals(name)) {
                salaryCounter += Integer.parseInt(employe[WORKING_HOUR_INDEX])
                        * Integer.parseInt(employe[INCOME_PER_HOUR_INDEX]);
            }
        }
        return salaryCounter;
    }

    public boolean isDateInLimits(String date, String dateFrom, String dateTo) {
        LocalDate testDate = LocalDate.parse(date, formatter);
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        return (!testDate.isBefore(startDate) && !testDate.isAfter(endDate));
    }
}
