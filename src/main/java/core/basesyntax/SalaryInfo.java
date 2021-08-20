package main.java.core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int userSalary = 0;
            for (String workingDate : data) {
                String[] userWorkingData = workingDate.split(" ");
                LocalDate userDate = LocalDate.parse(userWorkingData[0], FORMATTER);
                if (name.equals(userWorkingData[1]) && userDate.isAfter(fromDate)
                        && userDate.isBefore(toDate)) {
                    userSalary += Integer.parseInt(
                            userWorkingData[2]) * Integer.parseInt(userWorkingData[3]);
                }
            }
            result.append(name).append(" - ").append(userSalary).append(System.lineSeparator());
        }
        return result.toString();
    }
}
