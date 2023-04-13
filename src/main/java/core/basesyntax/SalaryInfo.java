package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int moneyEarned = 0;
        StringBuilder result = new StringBuilder();
        LocalDate dateFromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToDate = LocalDate.parse(dateTo, formatter);
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String nameCurrentEmployee : names) {
            moneyEarned = 0;
            for (String dateInfoCurrent : data) {
                String[] currentDayInfo = dateInfoCurrent.split(" ");
                LocalDate dateCurrentDate = LocalDate.parse(currentDayInfo[0], formatter);
                if (dateCurrentDate.isAfter(dateFromDate) && (dateCurrentDate.isBefore(dateToDate)
                        || dateCurrentDate.isEqual(dateToDate))
                        && nameCurrentEmployee.equals(currentDayInfo[1])) {
                    moneyEarned = moneyEarned + Integer.parseInt(currentDayInfo[2])
                           * Integer.parseInt(currentDayInfo[3]);
                }
            }
            result.append(System.lineSeparator()).append(nameCurrentEmployee)
                    .append(" - ").append(moneyEarned);
        }
        return result.toString();
    }
}
