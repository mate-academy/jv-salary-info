package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        LocalDate dateFromLocalTime = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToLocalTime = LocalDate.parse(dateTo, dateTimeFormatter);
        LocalDate currentEmployeeDate;
        String[] currentEmployee;
        int hoursWorked;
        int moneyPerHour;
        int totalIncome;

        for (String name : names) {
            totalIncome = 0;
            for (String datum : data) {
                if (datum.contains(name)) {
                    currentEmployee = datum.split(" ");
                    currentEmployeeDate = LocalDate.parse(currentEmployee[0],
                            dateTimeFormatter);
                    if ((currentEmployeeDate.isAfter(dateFromLocalTime)
                            && currentEmployeeDate.isBefore(dateToLocalTime))
                            || currentEmployeeDate.equals(dateToLocalTime)) {
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
