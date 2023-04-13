package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final StringBuilder result = new StringBuilder();
    private static final int dateIndex = 0;
    private static final int nameIndex = 1;
    private static final int workingHourIndex = 2;
    private static final int incomePerDayIndex = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int moneyEarned = 0;
        result.delete(0,result.length());
        LocalDate dateFromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToDate = LocalDate.parse(dateTo, formatter);
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String nameCurrentEmployee : names) {
            moneyEarned = 0;
            for (String dateInfoCurrent : data) {
                String[] currentDayInfo = dateInfoCurrent.split(" ");
                LocalDate dateCurrentDate = LocalDate.parse(currentDayInfo[dateIndex], formatter);
                if (dateCurrentDate.isAfter(dateFromDate) && (dateCurrentDate.isBefore(dateToDate)
                        || dateCurrentDate.isEqual(dateToDate))
                        && nameCurrentEmployee.equals(currentDayInfo[nameIndex])) {
                    moneyEarned = moneyEarned + Integer.parseInt(currentDayInfo[workingHourIndex])
                           * Integer.parseInt(currentDayInfo[incomePerDayIndex]);
                }
            }
            result.append(System.lineSeparator()).append(nameCurrentEmployee)
                    .append(" - ").append(moneyEarned);
        }
        return result.toString();
    }
}
