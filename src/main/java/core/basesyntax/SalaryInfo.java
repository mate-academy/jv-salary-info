package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(startDate.format(FORMATTER)).append(" - ").append(endDate.format(FORMATTER))
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] splitString = record.split(" ");
                String date = splitString[0];
                String employeeName = splitString[1];
                int hoursWorked = Integer.parseInt(splitString[2]);
                int payRate = Integer.parseInt(splitString[3]);
                LocalDate recordDate = LocalDate.parse(date, FORMATTER);
                if (name.equals(employeeName) && !recordDate.isBefore(startDate)
                        && !recordDate.isAfter(endDate)) {
                    totalSalary += hoursWorked * payRate;
                }
            }
            builder.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        String result = builder.toString();
        String lineSeparator = System.lineSeparator();
        if (result.endsWith(lineSeparator)) {
            result = result.substring(0, result.length() - lineSeparator.length());
        }
        return result;
    }
}
