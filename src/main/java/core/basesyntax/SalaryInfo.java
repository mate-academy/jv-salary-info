package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int FirstIndex = 0;
        final int HourIndex = 2;
        final int SalaryIndex = 3;
        final String Separator = System.lineSeparator();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromData = LocalDate.parse(dateFrom,dateTimeFormatter);
        LocalDate toData = LocalDate.parse(dateTo,dateTimeFormatter);
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
                String[] parts = record.split(" ");
                LocalDate currentData = LocalDate.parse(parts[FirstIndex],dateTimeFormatter);
                int dateValue = currentData.getYear() * 10000
                        + currentData.getMonthValue() * 100 + currentData.getDayOfMonth();

                if (parts[1].equals(name) && (dateValue >= startDateValue
                        && dateValue <= endDateValue)) {
                    int hoursWorked = Integer.parseInt(parts[HourIndex]);
                    int payPerHour = Integer.parseInt(parts[SalaryIndex]);
                    totalSalary += hoursWorked * payPerHour;
                }
            }
            result.append(name).append(" - ").append(totalSalary).append(Separator);
        }
        return result.toString().trim();
    }
}
