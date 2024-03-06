package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATA_SPLITTER = " ";
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED = 2;
    private static final int PAY_PER_HOUR = 3;
    private static final String SEPARATOR = System.lineSeparator();
    private static final DateTimeFormatter DATE_TIME_FORMATER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromData = LocalDate.parse(dateFrom,DATE_TIME_FORMATER);
        LocalDate toData = LocalDate.parse(dateTo,DATE_TIME_FORMATER);
        int startDateValue = fromData.getYear() * 10000
                + fromData.getMonthValue() * 100 + fromData.getDayOfMonth();
        int endDateValue = toData.getYear() * 10000
                + toData.getMonthValue() * 100 + toData.getDayOfMonth();
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom);
        result.append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] parts = record.split(DATA_SPLITTER);
                LocalDate currentData = LocalDate.parse(parts[DATA_INDEX],DATE_TIME_FORMATER);
                int dateValue = currentData.getYear() * 10000
                        + currentData.getMonthValue() * 100 + currentData.getDayOfMonth();
                if (parts[1].equals(name) && (dateValue >= startDateValue
                        && dateValue <= endDateValue)) {
                    int hoursWorked = Integer.parseInt(parts[HOURS_WORKED]);
                    int payPerHour = Integer.parseInt(parts[PAY_PER_HOUR]);
                    totalSalary += hoursWorked * payPerHour;
                }
            }
            result.append(name).append(" - ").append(totalSalary).append(SEPARATOR);
        }
        return result.toString().trim();
    }
}
