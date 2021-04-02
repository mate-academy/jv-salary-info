package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        LocalDate dateFromTime = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate dateToTime = LocalDate.parse(dateTo, TIME_FORMATTER);

        for (String name : names) {
            int hoursWorked;
            int moneyPerHour;
            int totalIncome = 0;
            for (String datum : data) {
                if (datum.contains(name)) {
                    String[] currentEmployee = datum.split(" ");
                    LocalDate currentEmployeeDate = LocalDate.parse(currentEmployee[0],
                            TIME_FORMATTER);
                    if ((currentEmployeeDate.isAfter(dateFromTime)
                            && currentEmployeeDate.isBefore(dateToTime))
                            || currentEmployeeDate.equals(dateFromTime)
                            || currentEmployeeDate.equals(dateToTime)) {
                        hoursWorked = Integer.parseInt(currentEmployee[2]);
                        moneyPerHour = Integer.parseInt(currentEmployee[3]);
                        totalIncome += hoursWorked * moneyPerHour;
                    }
                }
            }
            stringBuilder.append(name).append(" - ").append(totalIncome).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
